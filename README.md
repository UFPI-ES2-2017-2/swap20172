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
export JAVA_HOME="/opt/java/jdk"
export CLASSPATH="$JAVA_HOME/lib":$CLASSPATH
export PATH="$JAVA_HOME/bin":$PATH
export MANPATH="$JAVA_HOME/man":$MANPATH
~~~

Salve as alterações. Agora crie links para acessar os comandos *java* e *javac*.

~~~
$ ln -s /opt/java/jdk/bin/java /usr/local/bin/
$ ln -s /opt/java/jdk/bin/javac /usr/local/bin/
~~~

Pronto. Para testar digite no terminal:

~~~
$ java -version
$ javac -version
~~~

## Instalação Android Studio

Faça o download no link
