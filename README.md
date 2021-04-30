# RES-2021-Prank-SMTP

## Description du projet

Ce projet fournit une application client en Java pour créer des prank par mail. Le client envoi un certain message par mail de la part d'une adresse mail à une ou plusieurs autres adresses mails. Les destinataires désignés des mails penseront qu'il ont reçu un mail de l'expéditeur également désigné dans le client. 

## Instruction d'installation du serveur SMTP sur Docker



## Configuration

Une fois le repository cloné il faut configurer le projet. Les seuls fichiers auxquelles des modifications peuvent être apportées se situent dans le dossier config. 

- Fichier "config.properties" :

  On peut spécifier l'adresse IP du serveur SMTP ainsi que son numéro de port. On peut aussi décider du nombre de groupe de personne qui vont être victimes du prank et configurer l'adresse mail du correspondant témoin caché dans le but de pouvoir vérifier si le mail s'est bien envoyé. 

  Les noms des paramètres ne doivent pas être modifiés.

- Fichier "messages.txt" :

  On peut y écrire le contenu des mails et les séparer par "==". Ne pas oublier d'en mettre après le dernier mail sans aucun autre caractère (espace, retour à la ligne...). 

- Fichier "victims.txt"

  On y écrit la liste des emails des victimes qui vont soit envoyer, soit recevoir le message prank. Il faut écrire une adresse mail par ligne. 

### Remarque 

- Le nombre de victimes divisé par le nombre de groupe doit être plus grand ou égale à 3! Il doit donc pouvoir y avoir au minimum 3 personnes par groupe.


## Lancement du client

Une fois ces fichiers configurés, il faut exécuter le script build.sh. Ceci produira un fichier .jar. Pour lancer le client il suffit d'exécuter la commande suivante : 

```shell
java -jar Labo04-SMTP-1.0-SNAPSHOT-launcher.jar
```

Ceci lance le client en mode interactif. Pour lancer le client en mode automatique (les mails seront envoyés une fois sans attendre de confirmation), exécutez cette commande : 

```shell
java -jar Labo04-SMTP-1.0-SNAPSHOT-launcher.jar -noui
```

Si vous voulez bouger le fichier .jar dans un autre dossier, vous devez bouger avec lui le dossier config. Ce dossier doit toujours se trouver avec le .jar pour qu'il puisse fonctionner correctement.

## Fonctionnement du client

Le client va créer un nombre défini de groupe en choisissant aléatoirement des adresses mail dans la liste victims.txt pour désigner un expéditeur et plusieurs destinataires (2 au minimum). Le contenu du mail est lui aussi choisi aléatoirement parmi les messages présents dans le fichier messages.txt

On peut ensuite lancer l'application et suivre les instructions qui s'affichent. Si l'application est lancée en mode intéractif, un récapitulatif de la configuration, la liste des adresses mails et la liste des messages s'afficheront. Si tout est bien configuré, il faut entrer "ok" et la liste des mails envoyés avec les différents expéditeurs et destinataires s'afficheront. Une dernière confirmation d'envoi des mails s'affichera. On peut ensuite soit recommencer en entrant "go", soit quitter le programme en entrant "stop". Une fois les mails envoyés, l'utilisateur aura la possibilité de relancer de nouveau des mails (avec des groupes différents tirés aléatoirement) ou d'arreter le programme.

Si l'application n'est pas lancée en mode intéractif, le client enverra les mails, puis s'arretera.