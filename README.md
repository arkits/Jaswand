<h1 align="center">Jaswand</h1>
<div align="center">
<em>Create beautiful reports, right from Java!</em> <br> <br>
<img src="https://github.com/arkits/jaswand/workflows/Java%20CI/badge.svg"></img>
</div>

## About

- Jaswand applies the concept of re-usable web components and offers a Java Library to generate HTML reports quickly. 
- Jaswand makes use of the pre-existing data models from [j2html](https://github.com/tipsy/j2html) to render HTML files.
- [MaterializeCSS](https://github.com/Dogfalo/materialize) 1.0 is used as the CSS framework. 
- sourceCompatibility is 1.8. 
  
## Samples

You can view proof-of-concept sample of a Jaswand generated report [here](https://arkits.github.io/jaswand/samples/sample.html).  

More reports and code samples are available in the `/example/` directory.

## Documentation

Make sure to check out the [Jaswand Docs](https://arkits.github.io/jaswand/).

### Getting Started

Jaswand is available via the [Central](https://mvnrepository.com/artifact/io.github.arkits/jaswand). Add it your project as a dependency:

```groovy
compile group: 'io.github.arkits', name: 'jaswand', version: '0.0.2'
```

### Usage

Basic Usage

```java
// First create a Report object
Report report = new Report("Benchmark Report");

// and then export your Report as a HTML!
report.export("out/rendered.html");
```

Creating and Adding Elements

```java
// Create an ElementFactory
ElementFactory elementFactory = new ElementFactory();

// Generate a ReportCard element using the ElementFactory
ContainerTag card = elementFactory.reportCard(
    "Time Taken to Process", "20 ms");

// Add the card into your report
report.add(card);

```
