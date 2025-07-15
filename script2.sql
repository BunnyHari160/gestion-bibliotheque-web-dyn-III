CREATE TABLE abonnement (
    id_abonnement INT AUTO_INCREMENT PRIMARY KEY,
    id_adherent INT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    FOREIGN KEY (id_adherent) REFERENCES adherent(id_adherent)
);

SELECT *, (CURDATE() BETWEEN date_debut AND date_fin) AS validite
FROM abonnement;

-- Exemple (à adapter si les ID changent)
INSERT INTO abonnement (id_adherent, date_debut, date_fin)
VALUES
(1, '2025-02-01', '2025-07-24'),  -- ETU001 → OK
(2, '2025-02-01', '2025-07-01'),  -- ETU002 → KO
(3, '2025-04-01', '2025-12-01'),  -- ETU003 → OK
(4, '2025-07-01', '2026-07-01'),  -- ENS001 → OK
(5, '2025-08-01', '2026-05-01'),  -- ENS002 → KO
(6, '2025-07-01', '2026-06-01'),  -- ENS003 → OK
(7, '2025-06-01', '2025-12-01'),  -- PROF001 → OK
(8, '2024-10-01', '2025-06-01');  -- PROF002 → KO

ALTER TABLE abonnement ADD COLUMN validite BOOLEAN;
UPDATE abonnement
SET validite = (CURDATE() BETWEEN date_debut AND date_fin);
