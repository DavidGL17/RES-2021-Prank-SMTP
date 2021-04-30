# RES-2021-Prank-SMTP

## Description du projet

Ce projet fournit une application client en Java pour créer des prank par mail. Le client envoi un certain message par mail de la part d'une adresse mail à une ou plusieurs autres adresses mails. Les destinataires désignés des mails penseront qu'il ont reçu un mail de l'expéditeur également désigné dans le client. 

## Instruction d'installation du serveur SMTP sur Docker

Pour utiliser cette application, il vous faudra un serveur SMTP qui pourra recevoir ces mails. Si vous voulez avant de envoyer vos mails vérifier que le procédé se déroule sans encombre, nous avons mis a votre disposition un serveur SMPT test, encapsulé dans une image docker. Ce serveur est tiré du projet [MockMock](https://github.com/tweakers/MockMock). Ce serveur vous permet de lui envoyer des mails, et de consulter sur une page web le contenu des mails reçus, et de vérifier que ceux-ci sont bien reçus.

Pour pouvoir accéder a ce serveur, il suffit d'aller dans le dossier docker et exécuter le script **build-image.sh**, qui va créer l'image contenant le serveur MockMock. Puis, en éxécutant le script **run-containers.sh** le container contenant le serveur MockMock sera lancé. Pour vous connecter a ce serveur, il suffira de vous connecter en loclahost aux ports :  

- 8282 pour avoir l'affichage sur une page des mails entrant dans le serveur.

- 25 pour le serveur smtp. Si votre port 25 est déjà utilisé, ou bien vous n'avez pas les permissions nécessaires pour l'utiliser, vous pouvez changer le port du serveur smtp en modifiant le fichier run-container.sh. SI vous vouliez par exemple que le serveur écoute sur le port 2525 plutôt, il vous suffit de changer la commande comme ci-dessous : 
```shell
docker run -p 25:2525 -p 8282:8282 user/mock-mock-server
```

## Configuration

Une fois le repository cloné il faut configurer le projet. Les seuls fichiers auxquelles des modifications peuvent être apportées se situent dans le dossier config. 

- Fichier "config.properties" :

  On peut spécifier l'adresse IP du serveur SMTP ainsi que son numéro de port. On peut aussi décider du nombre de groupe de personne qui vont être victimes du prank et configurer l'adresse mail du correspondant témoin caché dans le but de pouvoir vérifier si le mail s'est bien envoyé. 

  Les noms des paramètres ne doivent pas être modifiés.

- Fichier "messages.txt" :

  On peut y écrire le contenu des mails et les séparer par "==". Ne pas oublier d'en mettre après le dernier mail sans aucun autre caractère (espace, retour à la ligne...). 

  S'il y a plus de groupes que de messages, l'application reutilisera certains messages.

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