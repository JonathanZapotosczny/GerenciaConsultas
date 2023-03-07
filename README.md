<h1 align="left">GerenciaConsultas - Gerenciamento de Consultas Nutricionais</h1>

<p align="left">API REST que permite criar, atualizar, buscar e deletar pacientes, nutricionistas e consultas.</p>

<h2 align="left">Tecnologias Utilizadas</h2>

<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="50" width="62" alt="java logo"  />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" height="50" width="62" alt="spring logo"  />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" height="50" width="62" alt="mysql logo"  />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" height="50" width="62" alt="docker logo"  />
</div>

<h2 align="left">Schemas JSON</h2>

Pacientes
```json
{
    "cpf": "101.088.369-08",
    "nome": "Felipe Paulo Dias",
    "email": "felipe.paulo@gmail.com",
    "telefone": "(41) 9 8631-4288",
    "dataNascimento": "02/02/2001",
    "logradouro": "Rua Pedro Aleixo",
    "numero": "658",
    "bairro": "Cidade Industrial",
    "cidade": "Curitiba",
    "cep": "81260-090",
    "sexo": "M",
    "altura": 1.86,
    "peso": 72.0,
    "observacoes": "sem observaçoes"
}
```

Nutricionistas
```json
{
    "crn": "CRN-3 123456",
    "nome": "Francisca dos Santos",
    "email": "francisca.santos@gmail.com",
    "telefone": "(41) 9 8119-2610"
}
```

Consultas
```json
{
    "paciente": 1,
    "nutricionista": 1,
    "dataConsulta": "04/03/2023 20:00:00",
}
```

<h2 align="left">Observações</h2>

<ul>
    <li>O JAVA 11 e o Docker precisam estar instalados em sua máquina</li>
    <li>Após clonar o projeto, abra o terminal na pasta raíz e execute o seguinte comando: docker-compose up -d</li>
    <li>Documentação disponível pelo Swagger em: http://localhost:8085/swagger-ui/index.html#/</li>
</ul>
