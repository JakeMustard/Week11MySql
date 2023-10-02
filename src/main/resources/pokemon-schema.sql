-- Drop tables if they exist
DROP TABLE IF EXISTS type_effectiveness;
DROP TABLE IF EXISTS move;
DROP TABLE IF EXISTS type;
DROP TABLE IF EXISTS pokemon;

-- Table to store Pokémon information
CREATE TABLE pokemon (
    pokemon_id INT AUTO_INCREMENT NOT NULL,
    pokemon_name VARCHAR(128) NOT NULL,
    base_health INT,
    base_attack INT,
    base_defense INT,
    base_speed INT,
    PRIMARY KEY (pokemon_id) -- Defining the primary key
);

-- Table to store types of Pokémon
CREATE TABLE type (
    type_id INT AUTO_INCREMENT NOT NULL,
    type_name VARCHAR(64) NOT NULL,
    PRIMARY KEY (type_id) -- Defining the primary key
);

-- Table to store moves and their types
CREATE TABLE move (
    move_id INT AUTO_INCREMENT NOT NULL,
    move_name VARCHAR(128) NOT NULL,
    move_type_id INT,
    PRIMARY KEY (move_id), -- Defining the primary key
    FOREIGN KEY (move_type_id) REFERENCES type(type_id) -- Defining foreign key relationship
);

-- Table to store type effectiveness
CREATE TABLE type_effectiveness (
    attacking_type_id INT NOT NULL,
    defending_type_id INT NOT NULL,
    effectiveness DECIMAL(4, 2) NOT NULL,
    PRIMARY KEY (attacking_type_id, defending_type_id), -- Defining the primary key
    FOREIGN KEY (attacking_type_id) REFERENCES type(type_id), -- Defining foreign key relationship
    FOREIGN KEY (defending_type_id) REFERENCES type(type_id) -- Defining foreign key relationship
);
