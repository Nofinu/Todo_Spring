### ToDo Spring Project


projet réaliser dans le cadre d'une formation cda,lors de l'apprentisage de Spring et de 
ThymLeaf

---
sujet du tp :

Le but du TP est d'appliquer les différentes notions liées à Spring boot.

>- Réaliser une application ToDo List en spring boot, L’application doit permettre :
>>- Ajouter une todo
>>- Modifier une todo
>>- Supprimer une todo
>>- Changer l’état d’une todo
>>- Afficher la liste des todos déjà réalisée.
>>- Afficher la liste des todos encore à faire
>- Une todo est caractérisée par (id, titre, description, date, etat)
---
-class : Todo
---
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    private boolean complete;
---


En partant d'u TP 1 ajoutez :

>- On souhaite rendre accessible la page d'ajout de todo uniquement pour des membres inscrits
>>- Ajoutez une page d'inscription d'utlisateur.
>>- Ajoutez une page de login pour accéder au formulaire.
>- On souhaite ajouter, également, une page de todos urgent pour les membres connectés.
>>- Ajoutez un lien pour mettre en urgent une todo
>>- Ajoutez une page pour la liste des todos urgents.

---
-class : User
---
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
---
lors de l'enregistrement du mot de passe en base de donnée il est crypté avec bcrypt.
