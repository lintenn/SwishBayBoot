![](img/SwishBay_logo_white.png) 
***
![](img/SwishBay_logo_black.png) 
***
# SwishBayBoot
Repository for the subject Web Application Technologies. SwishBayBoot is a web application for managing the sale (auctions) of products between users, similar to Ebay.
There is another version of [this web application based on Java EE](https://github.com/lintenn/SwishBay).

## English:
### Requirements:  
#### Seller - Galo Pérez Gallego 
- A seller must be able to register a product. _(does not imply putting it up for auction directly)_ :heavy_check_mark:
- A seller must be able to put a product up for sale (indicating the starting price and duration of the auction). :heavy_check_mark:
- A seller must be able to see the products they have for sale. :heavy_check_mark:
- A seller must be able to **search and apply search filters** on the products they have for sale. :heavy_check_mark:
- A seller must be able to see the products they have sold _(and to whom)_. :heavy_check_mark:
- A seller must be able to withdraw their registered or auctioned products or that they have sold. :heavy_check_mark:
- A seller must be able to edit their registered or auctioned products or that they have sold. :heavy_check_mark:
- A seller must be able to assign the product sold to the corresponding buyer _(and notify them)_. :heavy_check_mark:

#### Buyer - Miguel Oña Guerrero
- A buyer must be able to see the products that are registered by sellers. :heavy_check_mark:
- A buyer must be able to see the products that the sellers are auctioning. :heavy_check_mark:
- A buyer must be able to bid on a product with the intention of buying it. :heavy_check_mark:
- A buyer must be able to mark products (whether on auction or not) as favorites for **follow-ups**. :heavy_check_mark:
- A buyer must be able to add funds to their account. :heavy_check_mark:
- A buyer must be able to see in a list the products that they have bought _(and from whom)_. :heavy_check_mark:
- A buyer must be able to **search and apply search filters** on the products they have purchased. :heavy_check_mark:
- A buyer must be able to see the products they follow as favorites in a list. :heavy_check_mark:
- A buyer must be able to **search and apply search filters** on the products they follow as favorites. :heavy_check_mark:
- A buyer must be able to remove products from those they follow as favorites. :heavy_check_mark:

#### Administrator and Login/Register - Luis Miguel García Marín
- An administrator must be able to see the system categories in a list. :heavy_check_mark:
- An administrator must be able to add categories to the system. :heavy_check_mark:
- An administrator must be able to update system categories. :heavy_check_mark:
- An administrator must be able to remove categories from the system. :heavy_check_mark:
- An administrator must be able to **search and apply search filters** on the categories. :heavy_check_mark:
- An administrator must be able to see the users of the system in a list. :heavy_check_mark:
- An administrator must be able to add users to the system. :heavy_check_mark:
- _An administrator must be able to modify the users of the system_. :heavy_check_mark:
- An administrator must be able to remove users from the system (except himself). :heavy_check_mark:
- An administrator must be able to **search and apply search filters** on users. :heavy_check_mark:
- An administrator must be able to see the system's products in a list. :heavy_check_mark:
- An administrator must be able to modify system products. :heavy_check_mark:
- An administrator must be able to remove products from the system. :heavy_check_mark:
- An administrator must be able to **search and apply search filters** on the products. :heavy_check_mark:
- An administrator must be able to register (or assign the corresponding permissions) to marketing users. :heavy_check_mark: 
- A USER must be able to register in the system as a Seller or Buyer (administrators are added to the database and these will add the marketing staff), indicating Name, Surname, Address, City of residence, _Age_, Sex (in Buyers also preferred categories will be indicated).:heavy_check_mark:
- A USER must be able to log into the system. :heavy_check_mark:

#### Marketing - Angel Joaquín Rodríguez Mercado
- A marketing staff must be able to see the **buyer** users of the system in a list. :heavy_check_mark:
- A marketing staff must be able to **search and apply search filters** on the purchasing users of the system. :heavy_check_mark:
- A marketing staff must be able to create groups (also called *lists*) of users. :heavy_check_mark:
- A marketing staff must be able to modify their created groups (such as their name). :heavy_check_mark:
- A marketing staff must be able to add users to their created groups. :heavy_check_mark:
- A marketing staff must be able to remove users from their created groups. :heavy_check_mark:
- A marketing staff must be able to delete their created groups. :heavy_check_mark:
- A marketing staff must be able to consult the messages of groups of users. :heavy_check_mark:
- A marketing staff must be able to send messages to their user groups. :heavy_check_mark:
- A marketing staff must be able to modify messages from their user groups. :heavy_check_mark:
- A marketing staff must be able to delete messages sent to their user groups. :heavy_check_mark:
- A marketing staff must be able to manage receiving messages in the message inbox of buyer users. :heavy_check_mark:
- A marketing staff must be able to notify users that their favorite products have been opened for auction. :heavy_check_mark:
- A marketing staff must be able to notify buyers when the auction process has closed and if they finally keep the product. :heavy_check_mark:


## Spanish:
### Requisitos: :heavy_check_mark:   :x: 
#### Vendedor - Galo Pérez Gallego 
- Un vendedor debe poder registrar un producto. _(no implica ponerlo en puja directamente)_ :heavy_check_mark:
- Un vendedor debe poder poner para venta en puja un producto (indicando precio de salida y duración de la puja). :heavy_check_mark:
- Un vendedor debe poder ver los productos que tiene en venta. :heavy_check_mark:
- Un vendedor debe poder **buscar y aplicar filtrados** de búsqueda sobre los productos que tiene en venta. :heavy_check_mark:
- Un vendedor debe poder ver los productos que ha vendido _(y a quién)_. :heavy_check_mark:
- Un vendedor debe poder retirar sus productos registrados o en puja o que ha vendido. :heavy_check_mark:
- Un vendedor debe poder editar sus productos registrados o en puja o que ha vendido. :heavy_check_mark:
- Un vendedor debe poder realizar la asignación del producto vendido al comprador que corresponda _(y lo notifica)_. ❔

#### Comprador - Miguel Oña Guerrero
- Un comprador debe poder ver los productos que hay registrados por vendedores. :heavy_check_mark:
- Un comprador debe poder ver los productos que hay en puja por vendedores. :heavy_check_mark:
- Un comprador debe poder pujar un producto en puja con la intención de comprarlo. :heavy_check_mark:
- Un comprador debe poder marcar productos (estén en puja o no) como favoritos para hacer **seguimientos**. :heavy_check_mark:
- Un comprador debe poder añadir fondos a su cuenta. :heavy_check_mark:
- Un comprador debe poder ver en un listado los productos que ha comprado _(y a quién)_. :heavy_check_mark:
- Un comprador debe poder **buscar y aplicar filtrados** de búsqueda sobre los productos que ha comprado. :heavy_check_mark:
- Un comprador debe poder ver en un listado los productos que sigue como favoritos. :heavy_check_mark:
- Un comprador debe poder **buscar y aplicar filtrados** de búsqueda sobre los productos que sigue como favoritos. :heavy_check_mark:
- Un comprador debe poder quitar productos de los que sigue como favoritos. :heavy_check_mark:

#### Administrador y Login/Registro - Luis Miguel García Marín
- Un administrador debe poder ver en un listado las categorías del sistema. :heavy_check_mark:
- Un administrador debe poder añadir categorías al sistema. :heavy_check_mark:
- Un administrador debe poder actualizar categorías del sistema. :heavy_check_mark:
- Un administrador debe poder eliminar categorías del sistema. :heavy_check_mark:
- Un administrador debe poder realizar **filtrados y búsquedas** sobre las categorías. :heavy_check_mark:
- Un administrador debe poder ver en un listado los usuarios del sistema. :heavy_check_mark:
- Un administrador debe poder añadir usuarios al sistema. :heavy_check_mark:
- _Un administrador debe poder modificar los usuarios del sistema_. :heavy_check_mark:
- Un administrador debe poder eliminar usuarios del sistema (menos a sí mismo). :heavy_check_mark:
- Un administrador debe poder realizar **filtrados y búsquedas** sobre los usuarios. :heavy_check_mark:
- Un administrador debe poder ver en un listado los productos del sistema. :heavy_check_mark:
- Un administrador debe poder modificar productos del sistema. :heavy_check_mark:
- Un administrador debe poder eliminar productos del sistema. :heavy_check_mark:
- Un administrador debe poder realizar **filtrados y búsquedas** sobre los productos. :heavy_check_mark:
- Un administrador debe poder dar de alta (o asignar los permisos correspondientes) a los usuarios de marketing. :heavy_check_mark: 
- Un USUARIO debe poder registrarse en el sistema como Vendedor o Comprador (los administradores se añaden en la base de datos y estos añadirán al personal de marketing), indicando Nombre, Apellidos, Domicilio, Ciudad de residencia, _Edad_, Sexo (en los Compradores además se indicarán las categorías preferidas). :heavy_check_mark:
- Un USUARIO debe poder iniciar sesión en el sistema. :heavy_check_mark:

#### Marketing - Angel Joaquín Rodríguez Mercado
- Un personal de marketing debe poder ver en un listado los usuarios **compradores** del sistema. :heavy_check_mark:
- Un personal de marketing debe poder **buscar y aplicar filtrados** de búsqueda sobre los usuarios compradores del sistema. :heavy_check_mark:
- Un personal de marketing debe poder crear grupos (también llamadas *listas*) de usuarios. :heavy_check_mark:
- Un personal de marketing debe poder modificar sus grupos creados (como su nombre). :heavy_check_mark:
- Un personal de marketing debe poder añadir usuarios a sus grupos creados. :heavy_check_mark:
- Un personal de marketing debe poder eliminar usuarios de sus grupos creados. :heavy_check_mark:
- Un personal de marketing debe poder eliminar sus grupos creados. :heavy_check_mark:
- Un personal de marketing debe poder consultar los mensajes de grupos de usuarios. :heavy_check_mark:
- Un personal de marketing debe poder enviar mensajes a sus grupos de usuarios. :heavy_check_mark:
- Un personal de marketing debe poder modificar mensajes de sus grupos de usuarios. :heavy_check_mark:
- Un personal de marketing debe poder eliminar mensajes enviados a sus grupos de usuarios. :heavy_check_mark:
- Un personal de marketing debe poder gestionar la recepción de los mensajes en la bandeja de entrada de mensajes de los usuarios compradores. :heavy_check_mark:
- Un personal de marketing debe poder notificar a los usuarios de que sus productos favoritos se han abierto para puja. :heavy_check_mark:
- Un comprador debe poder ser notificado cuando se ha cerrado el proceso de puja y si si finalmente se queda con el producto. :heavy_check_mark: