# language:pt
# encoding:UTF-8

@Login
Funcionalidade: Efetuar login

 Cenario: Efetuar login com sucesso
    Dado efetuo a busca do usuario com <id>
    E obtenho os dados de login "firstname" e "password"
    E gero o "token" com sucesso
    Quando envio uma requisicap POST para url de "Login"
    Entao recebo <statusCode>

    Exemplos:
    |id | statusCode|
    | 1 | 200       |


