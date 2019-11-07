<h1 align="center">Jaswand</h1>
<p align="center">
<em>Create beautiful reports, right from Java!</em>
</p>

## About

Jaswand applies the concept of re-usable web components and offers a Java Library to generate HTML reports quickly. Jaswand makes use of the pre-existing data models from [j2html](https://github.com/tipsy/j2html) and uses [MaterializeCSS](https://github.com/Dogfalo/materialize) as the CSS framework. 

## Samples

You can view proof-of-concept sample of a Jaswand generated report [here](https://arkits.github.io/jaswand/examples/sample_render.html).  

More report and code samples are available in the `/example/` directory.

## Documentation

### Getting Started

Add the Jaswand dependency to your project:

```groovy
compile group: 'io.github.arkits', name: 'jaswand', version: '0.0.1'
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