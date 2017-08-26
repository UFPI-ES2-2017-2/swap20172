# swap20172
Trabalho da disciplina de Engenharia de Software 2, UFPI - 2017.2

## Pré-Requisitos

* Distribuição Linux baseada no Debian
* JDK 8
* Android Studio 2.3

## Instalação JDK

Acesse o link http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html para fazer o download do JDK.

Após o término do download, descompacte o arquivo e mova-o para a pasta **/opt/java**.

Agora edite o arquivo **/etc/profile**. Adicione as seguintes linhas no início do arquivo:

~~~
export JAVA_HOME="/opt/java/jdk1.8.0_xyz"
export CLASSPATH="$JAVA_HOME/lib":$CLASSPATH
export PATH="$JAVA_HOME/bin":$PATH
export MANPATH="$JAVA_HOME/man":$MANPATH
~~~

Salve as alterações. Agora crie links para acessar os comandos *java* e *javac*.

> ln -s /opt/java/jdk1.8.0_xyz/bin/java /usr/local/bin/

> ln -s /opt/java/jdk1.8.0_xyz/bin/javac /usr/local/bin/

Pronto. Para testar digite no terminal:

> java -version

> javac -version

## Instalação Android Studio

Faça o download no link https://developer.android.com/studio/index.html?hl=pt-br. Após o término do download, descompacte o arquivo e mova o programa para a pasta **/opt/android-studio**.

Vá até a pasta **/opt/android-studio/bin** e execute o arquivo **studio.sh** com o comando:

> ./studio.sh

Durante o processo de configuração:

* Não importe configurações de versões anteriores.
* Prefira a configuração padrão.

Pronto. Agora basta criar seu projeto.

## Criando o Projeto

Crie um projeto com:

* Application name: Swap
* Company domain: br.ufpi
* Blank Activity

## Consumindo dados de API


