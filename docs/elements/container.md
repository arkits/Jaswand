---
layout: default
title: Container
parent: Elements
---

# Container

From the [MaterializeCSS](https://materializecss.com/grid.html)'s documentation:

> The container class is not strictly part of the grid but is important in laying out content. It allows you to center your page content. The container class is set to ~70% of the window width. It helps you center and contain your page content. We use the container to contain our body content.

```java
// Create a container using the ElementFactory
ContainerTag container = elementFactory.container();

// Add elements to your container
container.with(
    // Your elements here...
);

// Add the container to your report
report.add(container);
```

---