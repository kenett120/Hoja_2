# Calculadora Postfija

Aplicación Java que evalúa expresiones en notación postfija (Reverse Polish Notation).

## ¿Qué es notación postfija?

En notación postfija, los operadores se colocan después de los operandos:
- `1 2 +` significa `1 + 2 = 3`
- `5 3 + 2 *` significa `(5 + 3) * 2 = 16`

## Cómo ejecutar el programa

### Opción 1: Con Maven (recomendado)

Si tienes Maven instalado:

```bash
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```

### Opción 2: Con Java puro (sin Maven)

Si solo tienes Java instalado:

```bash
# Compilar
javac -d target/classes src/main/java/org/example/*.java

# Ejecutar
java -cp target/classes org.example.Main
```

## Uso

### Opción 1: Leer desde archivo

El programa lee operaciones del archivo `datos.txt` (una operación por línea). Esta es la opción activa por defecto.

### Opción 2: Operaciones directas en código

Editar `Main.java` y descomentar las líneas:

```java
// System.out.println("1 2 + = " + calc.operate("1 2 +"));
```

Luego comentar la línea:
```java
calc.processFile("datos.txt");
```

## Formato de datos.txt

Una operación por línea, operandos y operadores separados por espacios:

```
1 2 +
5 3 -
4 5 *
20 4 /
5 3 + 2 *
```

## Operadores soportados

- `+` Suma
- `-` Resta
- `*` Multiplicación
- `/` División

## Ejecutar tests

Los tests requieren Maven:

```bash
mvn test
```
