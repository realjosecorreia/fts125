Feature: Compra no Site Extra

  @compra @estorno
	Scenario: Busca por Produto
		Given que acesso o site do Submarino
		When preencho o termo "smartphone" e clico na Lupa
		Then exibe a lista de produtos
	
	@estorno
	Scenario: Busca por Produto N�o Encontrado
		Given que acesso o site do Submarino
		When preencho o termo "QWEQWEQWEADASDASDASD" e clico na Lupa
		Then exibe a mensagem de produto nao encontrado