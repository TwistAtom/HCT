# ⚙️  HCT
Huffman Coding Tree


Implementation of a hash algorithm based on Huffman code using binary tree to improve the time complexity of encoding.
HCT allows you to encode text using a character frequency array.

Huffman code is a particular type of optimal prefix code that is commonly used for lossless data compression. [Learn more](https://en.wikipedia.org/wiki/Huffman_coding)


## 📚 Requirements :
You must first install the [JRE](https://www.java.com/fr/download/) containing the java virtual machine (JVM) in your runtime environment.


## 📥 Installation
```sh
$ git clone https://github.com/TwistAtom/HCT.git
```


## 🚀 Launch

Compile manually, you should get ".class" :
```sh
$ cd HCT
$ javac *.java
```
Run the main class (CodageHuffman) :
```sh
$ java CodageHuffman
```


## 🖊️  Example

```java
String texteInitial = "Tu n'es encore pour moi qu'un petit garçon tout semblable à cent mille petits garçons. " + "Et je n'ai pas besoin de toi. Et tu n'as pas besoin de moi non plus. Je ne suis pour toi qu'un renard semblable à cent mille renards. " + "Mais, si tu m'apprivoises, nous aurons besoin l'un de l'autre. Tu seras pour moi unique au monde. Je serai pour toi unique au monde.";
		
char caracteres[] = {'T', 'u', ' ', 'n', '\'', 'e', 's', 'c', 'o', 'r', 'p', 'm', 'i', 'q', 't', 'g', 'a', 'ç', 'b', 'l', 'à', '.', 'E', 'j', 'd', 'J', 'M', ',', 'v'};
double frequences[] = {2, 25, 66, 25, 8, 33, 24, 3, 23, 16, 11, 10, 22, 4, 16, 2, 18, 2, 7, 11, 2, 7, 2, 1, 7, 2, 1, 2, 1};

CodageHuffman secret = new CodageHuffman(caracteres, frequences);
		
String texteEncode = secret.encoder(texteInitial);
String texteDecode = secret.decoder(texteEncode);
```



