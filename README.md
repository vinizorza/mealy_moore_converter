# Conversor de máquidas de saída (Mealy e Moore)

Este programa converte de máquia de Mealy para máquina de Moore e vice e versa.

## Tecnologia utilizada
Java 8

## Autor
Vinicius Zorzanelli

## Procedimento para execução
Executar o comando no terminal:
```console
foo@bar:~$ java -jar converter.jar -i <input-filepath> -o <output-filepath>
```

## Procedimento para debug
Importar o pom.xml na IDE de preferência.

## Estrutura
```    
.
│   .gitignore
│   pom.xml                                         # Arquivo de build
│   README.md
│
├───src
│   ├───main
│   │   ├───java
│   │   │   │   Main.java                           # Classe principal
│   │   │   │
│   │   │   ├───core                                # Pacote contendo o core de conversão
│   │   │   │       Converter.java
│   │   │   │
│   │   │   ├───entity                              # Pacote com domínio da aplicação
│   │   │   │       Machine.java
│   │   │   │       MachineType.java
│   │   │   │       State.java
│   │   │   │       Transition.java
│   │   │   │
│   │   │   └───util                                # Pacote de classes utilitárias (Leitura e escrita)
│   │   │           Bundle.java
│   │   │           MachineReader.java
│   │   │           MachineWriter.java
│   │   │
│   │   └───resources
│   │       │   messages.properties
│   │       │
│   │       └───META-INF
│   │               MANIFEST.MF
│   │
│   └───test
│       └───java
│           ├───core
│           │       ConverterTest.java
│           │
│           └───util
│                   MachineReaderTest.java
│                   MachineWriterTest.java
│
└───test_examples
```

