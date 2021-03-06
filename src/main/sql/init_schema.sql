DROP TABLE IF EXISTS SpendingTags;
DROP TABLE IF EXISTS Spendings;
DROP TABLE IF EXISTS Tags;

CREATE TABLE Spendings(
	"id" SERIAL PRIMARY KEY,
	"author" VARCHAR(20) NOT NULL,
	"item" VARCHAR(50) NOT NULL,
	"date" DATE NOT NULL,
	"price" DECIMAL(13,2) NOT NULL);

CREATE TABLE Tags(
	"id" SERIAL PRIMARY KEY,
	"name" VARCHAR(20) NOT NULL);
ALTER TABLE Tags ADD UNIQUE (name);

CREATE TABLE SpendingTags(
	"spendingid" INTEGER REFERENCES Spendings(id),
	"tagid" INTEGER REFERENCES Tags(id) );