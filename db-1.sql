-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 09 mars 2023 à 20:48
-- Version du serveur : 10.4.20-MariaDB
-- Version de PHP : 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db-1`
--

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

CREATE TABLE `demande` (
  `id` int(30) NOT NULL,
  `cin` varchar(255) NOT NULL,
  `cv` varchar(255) NOT NULL,
  `lettreMotivation` varchar(255) NOT NULL,
  `cartegrise` varchar(255) NOT NULL,
  `competences` varchar(255) NOT NULL,
  `id_offre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `demande`
--

INSERT INTO `demande` (`id`, `cin`, `cv`, `lettreMotivation`, `cartegrise`, `competences`, `id_offre`) VALUES
(9, 'lname', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\metafitness\\src\\resources\\prix-renault.jpg', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\metafitness\\src\\resources\\Ipessentials.jpg', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\metafitness\\src\\resources\\MSO.jpg', 'lname', 24),
(10, '122333334', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\metafitness\\src\\resources\\prix-renault.jpg', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\metafitness\\src\\resources\\images.jpg', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\metafitness\\src\\resources\\download.jpg', 'lname', 24),
(11, '22229999', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\metafitness\\src\\resources\\prix-renault.jpg', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\metafitness\\src\\resources\\151053961_761845354447001_8240281168055219033_n.jpg', 'C:\\Users\\DELL\\Downloads\\spring\\web_project\\SpringBoot4DS2\\metafitness\\src\\resources\\151301788_251787176507513_7907279904664092926_n.jpg', 'lname', 24),
(13, '12345678', 'C:\\Users\\DELL\\IdeaProjects\\belhsan-landolsi-version-2\\Fi_Thnity-gestion-offre-demandeSSS\\src\\resources\\media\\laynezcode-logo.png', 'C:\\Users\\DELL\\IdeaProjects\\belhsan-landolsi-version-2\\Fi_Thnity-gestion-offre-demandeSSS\\src\\resources\\media\\empty-image.jpg', 'C:\\Users\\DELL\\IdeaProjects\\belhsan-landolsi-version-2\\Fi_Thnity-gestion-offre-demandeSSS\\src\\resources\\media\\empty-statistics.png', 'python', 24),
(14, '12345678', 'C:\\Users\\DELL\\IdeaProjects\\belhsan-landolsi-version-2\\Fi_Thnity-gestion-offre-demandeSSS\\src\\resources\\media\\empty-image.jpg', 'C:\\Users\\DELL\\IdeaProjects\\belhsan-landolsi-version-2\\Fi_Thnity-gestion-offre-demandeSSS\\src\\resources\\media\\empty-statistics.png', 'C:\\Users\\DELL\\IdeaProjects\\belhsan-landolsi-version-2\\Fi_Thnity-gestion-offre-demandeSSS\\src\\resources\\media\\empty-statistics.png', 'python', 24),
(16, '12345678', 'C:\\Users\\DELL\\IdeaProjects\\belhsan-landolsi-version-2\\Fi_Thnity-gestion-offre-demandeSSS\\src\\resources\\media\\empty-image.jpg', 'C:\\Users\\DELL\\IdeaProjects\\belhsan-landolsi-version-2\\Fi_Thnity-gestion-offre-demandeSSS\\src\\resources\\media\\empty-statistics.png', 'C:\\Users\\DELL\\IdeaProjects\\belhsan-landolsi-version-2\\Fi_Thnity-gestion-offre-demandeSSS\\src\\resources\\media\\empty-statistics.png', 'python', 24);

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE `offre` (
  `offre_id` int(50) NOT NULL,
  `metier` varchar(255) NOT NULL,
  `secteur` varchar(255) NOT NULL,
  `ville` varchar(255) NOT NULL,
  `Nombredeposte` varchar(255) NOT NULL,
  `salaire` varchar(255) NOT NULL,
  `dateOffre` date DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `offre`
--

INSERT INTO `offre` (`offre_id`, `metier`, `secteur`, `ville`, `Nombredeposte`, `salaire`, `dateOffre`) VALUES
(24, 'Agent administratif', 'sec', 'Bizerte', 'poste', 'sal', '2023-03-06'),
(26, 'Agent administratif', 'sector', 'Gabès', 'nbr', 'sal', '2023-03-06');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `demande`
--
ALTER TABLE `demande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_demande_offre` (`id_offre`);

--
-- Index pour la table `offre`
--
ALTER TABLE `offre`
  ADD PRIMARY KEY (`offre_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `demande`
--
ALTER TABLE `demande`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `offre`
--
ALTER TABLE `offre`
  MODIFY `offre_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `FK_demande_offre` FOREIGN KEY (`id_offre`) REFERENCES `offre` (`offre_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
