-- Schema and queries for crop management

-- 1) Tables
CREATE TABLE IF NOT EXISTS crops (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  variety VARCHAR(100),
  sowing_period VARCHAR(50),
  typical_yield_kg_per_ha NUMERIC,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS fields (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  area_ha NUMERIC NOT NULL,
  soil_type VARCHAR(50),
  location VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS plantings (
  id SERIAL PRIMARY KEY,
  crop_id INTEGER NOT NULL REFERENCES crops(id),
  field_id INTEGER NOT NULL REFERENCES fields(id),
  planted_on DATE NOT NULL,
  expected_harvest_on DATE,
  seed_rate_kg_ha NUMERIC,
  population INTEGER,
  status VARCHAR(30) DEFAULT 'growing', -- e.g. planned, growing, harvested, failed
  notes TEXT
);

CREATE TABLE IF NOT EXISTS inputs (
  id SERIAL PRIMARY KEY,
  planting_id INTEGER NOT NULL REFERENCES plantings(id),
  type VARCHAR(30) NOT NULL, -- fertilizer, pesticide, irrigation, other
  name VARCHAR(100),
  applied_on DATE NOT NULL,
  quantity NUMERIC,
  unit VARCHAR(20),
  notes TEXT
);

CREATE TABLE IF NOT EXISTS inspections (
  id SERIAL PRIMARY KEY,
  planting_id INTEGER NOT NULL REFERENCES plantings(id),
  inspector VARCHAR(100),
  inspected_on DATE NOT NULL,
  pest_incidence_level SMALLINT, -- 0..100 scale
  notes TEXT
);

CREATE TABLE IF NOT EXISTS yields (
  id SERIAL PRIMARY KEY,
  planting_id INTEGER NOT NULL REFERENCES plantings(id),
  harvested_on DATE NOT NULL,
  quantity_kg NUMERIC NOT NULL,
  moisture_pct NUMERIC,
  notes TEXT
);

CREATE TABLE IF NOT EXISTS rotations (
  id SERIAL PRIMARY KEY,
  field_id INTEGER NOT NULL REFERENCES fields(id),
  previous_crop_id INTEGER REFERENCES crops(id),
  next_crop_id INTEGER REFERENCES crops(id),
  season VARCHAR(50),
  notes TEXT
);

-- 2) Indexes for common queries
CREATE INDEX IF NOT EXISTS idx_plantings_expected_harvest ON plantings(expected_harvest_on);
CREATE INDEX IF NOT EXISTS idx_inputs_planting ON inputs(planting_id);
CREATE INDEX IF NOT EXISTS idx_yields_planting ON yields(planting_id);

-- 3) Example seed data (minimal)
INSERT INTO crops (name, variety, sowing_period, typical_yield_kg_per_ha) VALUES
('Maize','Hybrid A','Mar-May',9000),
('Rice','Irrigated variety','Jun-Jul',7000)
ON CONFLICT DO NOTHING;

INSERT INTO fields (name, area_ha, soil_type, location) VALUES
('Field 1', 2.5, 'loam', 'Zone A'),
('Field 2', 1.8, 'clay', 'Zone B')
ON CONFLICT DO NOTHING;

-- Example planting
INSERT INTO plantings (crop_id, field_id, planted_on, expected_harvest_on, seed_rate_kg_ha, population, status)
SELECT c.id, f.id, CURRENT_DATE - INTERVAL '60 days', CURRENT_DATE + INTERVAL '30 days', 25, 50000, 'growing'
FROM crops c, fields f
WHERE c.name = 'Maize' AND f.name = 'Field 1'
LIMIT 1;

-- 4) Useful queries

-- A) Upcoming harvests within next N days
-- replace :days with number, e.g. 30
SELECT p.id AS planting_id, c.name AS crop, f.name AS field, p.expected_harvest_on
FROM plantings p
JOIN crops c ON p.crop_id = c.id
JOIN fields f ON p.field_id = f.id
WHERE p.expected_harvest_on IS NOT NULL
  AND p.expected_harvest_on BETWEEN CURRENT_DATE AND (CURRENT_DATE + INTERVAL '30 days')
  AND p.status = 'growing'
ORDER BY p.expected_harvest_on;

-- B) Plantings with no pesticide applied in last X days (possible need)
SELECT p.id AS planting_id, c.name, f.name, MAX(i.applied_on) AS last_input
FROM plantings p
JOIN crops c ON p.crop_id = c.id
JOIN fields f ON p.field_id = f.id
LEFT JOIN inputs i ON i.planting_id = p.id AND i.type = 'pesticide'
WHERE p.status = 'growing'
GROUP BY p.id, c.name, f.name
HAVING (MAX(i.applied_on) IS NULL OR MAX(i.applied_on) < (CURRENT_DATE - INTERVAL '30 days'));

-- C) Total yield per crop (aggregate)
SELECT c.name AS crop, c.variety, SUM(y.quantity_kg) AS total_kg, COUNT(DISTINCT y.planting_id) AS harvests
FROM yields y
JOIN plantings p ON y.planting_id = p.id
JOIN crops c ON p.crop_id = c.id
GROUP BY c.name, c.variety
ORDER BY total_kg DESC;

-- D) Average yield per hectare per crop (requires field area)
SELECT c.name AS crop, AVG(y.quantity_kg / f.area_ha) AS kg_per_ha
FROM yields y
JOIN plantings p ON y.planting_id = p.id
JOIN fields f ON p.field_id = f.id
JOIN crops c ON p.crop_id = c.id
GROUP BY c.name
ORDER BY kg_per_ha DESC;

-- E) Rotation history for a field
SELECT r.*, pc.name AS previous_crop, nc.name AS next_crop
FROM rotations r
LEFT JOIN crops pc ON r.previous_crop_id = pc.id
LEFT JOIN crops nc ON r.next_crop_id = nc.id
WHERE r.field_id = 1
ORDER BY r.id DESC;

-- F) Inputs applied to a field in date range
-- replace dates as needed
SELECT i.*, c.name AS crop, f.name AS field
FROM inputs i
JOIN plantings p ON i.planting_id = p.id
JOIN crops c ON p.crop_id = c.id
JOIN fields f ON p.field_id = f.id
WHERE p.field_id = 1 AND i.applied_on BETWEEN '2025-01-01' AND '2025-12-31'
ORDER BY i.applied_on;

-- G) Alert: expected harvest passed but no yield recorded
SELECT p.id AS planting_id, c.name AS crop, f.name AS field, p.expected_harvest_on
FROM plantings p
JOIN crops c ON p.crop_id = c.id
JOIN fields f ON p.field_id = f.id
LEFT JOIN yields y ON y.planting_id = p.id
WHERE p.expected_harvest_on < CURRENT_DATE AND y.id IS NULL
ORDER BY p.expected_harvest_on;

-- 5) Notes:
-- - For MySQL, replace SERIAL with INT AUTO_INCREMENT and adjust CURRENT_TIMESTAMP/INTERVAL syntax.
-- - For stricter enums, change type VARCHAR(30) to ENUM(...) where supported.
-- - Add application-level validations for domain rules (seed rates, status transitions).

-- End of script

