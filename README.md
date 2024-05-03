### Inventory Control Project

> - Generic inventory control system for resale products that manages two databases, one for suppliers and the other for products.
> - Product purchase batches are recorded for each product. For each purchase lot, resale lots can be registered.
> - Available quantity and total Product and Purchase balance are maintained by the system.

### Supplier database:

> - Pessoa constitutes the basic supplier registration.
> - Endereco complements the instance of Pessoa with data on the supplier's address.
> - PessoaBO maintains a HashSet of objects of Pessoa (with Endereco instantiated in Pessoa). The PessoaBO class object is written to a file (and loaded) by the setDAO file manager class.

### Product Database:

> - Produto consists of the basic product registration. Each product maintains its own HashSet of purchase lots for the respective product.
> - Compra represents purchases of a certain quantity at a specific unit price for a specific product. Each Purchase will maintain its own HashSet of resales of the respective purchase batch.
> - Revenda represents resales deducted from a purchase lot.
> - Compraset is instantiated in Produto and maintains a HashSet of purchases for that product.
> - Revendaset is instantiated in Compra and maintains a HashSet of resales from that product's purchase batch.
> - ProductBO maintains a HashSet of products with all other nested HashSets. ProdutoBO is managed in file by the setDAO class.

<img src="https://github.com/GabrielFCosta/controle-de-estoque/assets/1496860/92cac5f9-a8bd-4875-bbb0-ba2637cd07a2" width="50%" height="50%"/>

##### Generation of unique codes (primary keys) from a record counter implemented in Colecao.

<img src="https://github.com/GabrielFCosta/controle-de-estoque/assets/1496860/3e93fb64-de8d-4d2b-a253-fa37627c6077" width="50%" height="50%"/>

##### Dependencies between packages following the MVC standard.

<img src="https://github.com/GabrielFCosta/controle-de-estoque/assets/1496860/b5f67715-56ce-4165-8ee1-fc3e0b2df4c1" width="50%" height="50%"/>

### File Management:

> - Managed by the setDAO class for both PessoaBO and ProdutosBO using object streams.
> - Two binary files generated: pessoa.bin for suppliers, produtos.bin for products, purchases and resales.
> - In order to do this, all classes that contain data to be written to a file need to implement the Serializable interface and have a serial VersionUID.
> - Writing data to file uses native Java classes FileOutputStream and ObjectOutputStream.
> - Loading files into memory (in HashSets) uses native Java classes FileInputStream and ObjectInputStream.


View the javadoc [here](https://gabrielfcosta.github.io/controle-de-estoque/) (in Portuguese).

