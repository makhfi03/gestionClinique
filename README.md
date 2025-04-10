# Projet de Gestion de Clinique


![image](https://github.com/user-attachments/assets/a7023a06-0444-4458-94ce-e8082962e730)


## Contexte

Le projet Gestion de Clinique vise à développer une application logicielle pour automatiser les processus administratifs et médicaux d'une clinique. Cette solution permettra de centraliser les données des patients, des médecins, des rendez-vous et des prescriptions, améliorant ainsi l'efficacité et la qualité des soins.

## Problématique
Actuellement, la clinique utilise des systèmes manuels (dossiers papier, fichiers Excel) pour gérer :

Les dossiers patients

La planification des rendez-vous

La gestion du personnel médical

La facturation

Cela entraîne :

✅ Des erreurs humaines (doublons, perte de données).

✅ Une perte de temps dans la recherche d'informations.

✅ Une difficulté à générer des rapports (statistiques, suivi des patients).


## Objectifs

L'application aura pour but de :

✔ Numériser la gestion des patients (création, modification, consultation).

✔ Faciliter la prise de rendez-vous (disponibilité des médecins, alertes de conflits).

✔ Automatiser la facturation (génération de factures, suivi des paiements).

✔ Sécuriser les données (accès par rôles : administrateur, médecin, secrétaire).

✔ Générer des statistiques (fréquentation, revenus, diagnostics fréquents).

## 📚 Explication rapide des packages

config : Fichier de configuration Hibernate (connexion DB)

dao : Accès aux données (CRUD) via Hibernate

entities : Tes classes @Entity (Patient, Médecin, Consultation, User)

controller : Tes servlets pour gérer les actions utilisateur (ajout, affichage, etc.)

util : Classe utilitaire (HibernateUtil)

test : Scripts pour créer des objets de test


## Diagramme de Classe
