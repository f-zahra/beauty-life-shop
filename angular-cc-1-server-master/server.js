const express = require("express");
const fs = require("fs");
const cors = require("cors");
const path = require("path");

const app = express();
const port = 3000;

// Cors configuration - Allows requests from localhost:4200
const corsOptions = {
  origin: "http://localhost:4200",
  optionsSuccessStatus: 204,
  methods: "GET, POST, PUT, DELETE",
};

// Use cors middleware
app.use(cors(corsOptions));

// Serve static files from the 'dist' directory
app.use(express.static(__dirname + "/public"));

// Use express.json() middleware to parse JSON bodies of requests
app.use(express.json());

// GET route - Allows to get all the products
// example: localhost:3000/clothes?page=0&perPage=2
app.get("/products", (req, res) => {
  const page = parseInt(req.query.page) || 0;
  const perPage = parseInt(req.query.perPage) || 10;
  console.log();
  fs.readFile("db.json", "utf8", (err, data) => {
    if (err) {
      console.log(err);
      res.status(500).send("Internal Server Error");
      return;
    }

    //parsing data
    let jsonData;
    try {
      jsonData = JSON.parse(data);
    } catch (error) {
      console.log(error);
      res.status(500).send("Error parsing JSON data");
      return;
    }

    if (!jsonData || !jsonData.products) {
      res.status(500).send("Invalid JSON data structure");
      return;
    }
    const start = page * perPage;
    const end = start + perPage;
    console.log(jsonData);
    const result = jsonData.products.slice(start, end);

    res.status(200).json({
      products: result,
      total: jsonData.products.length,
      page,
      perPage,
      totalPages: Math.ceil(jsonData.products.length / perPage),
    });
  });
});

// POST route - Allows to add a new item
// example: localhost:3000/clothes
/*
  body: {
    "image": "https://your-image-url.com/image.png",
    "name": "T-shirt",
    "price": "10",
    "rating": 4
  }
*/
app.post("/products", (req, res) => {
  const { image, name, price, rating } = req.body;

  fs.readFile("db.json", "utf8", (err, data) => {
    if (err) {
      console.log(err);
      res.status(500).send("Internal Server Error");
      return;
    }

    const jsonData = JSON.parse(data);

    const maxId = jsonData.products.reduce(
      (max, item) => Math.max(max, item.id),
      0
    );

    const newItem = {
      id: maxId + 1,
      image,
      name,
      price,
      rating,
    };

    jsonData.products.push(newItem);

    fs.writeFile("db.json", JSON.stringify(jsonData), (err) => {
      if (err) {
        console.log(err);
        res.status(500).send("Internal Server Error");
        return;
      }

      res.status(201).json(newItem);
    });
  });
});

// PUT route - Allows to update an item
// example: localhost:3000/clothes/1
/*
  body: {
    "image": "https://your-image-url.com/image.png",
    "name": "T-shirt",
    "price": "10",
    "rating": 4
  }
*/
app.put("/clothes/:id", (req, res) => {
  const id = parseInt(req.params.id);
  const { image, name, price, rating } = req.body;

  fs.readFile("db.json", "utf8", (err, data) => {
    if (err) {
      console.log(err);
      res.status(500).send("Internal Server Error");
      return;
    }

    const jsonData = JSON.parse(data);

    const index = jsonData.products.findIndex((item) => item.id === id);

    if (index === -1) {
      res.status(404).send("Not Found");
      return;
    }

    jsonData.products[index] = {
      id,
      image,
      name,
      price,
      rating,
    };

    fs.writeFile("db.json", JSON.stringify(jsonData), (err) => {
      if (err) {
        console.log(err);
        res.status(500).send("Internal Server Error");
        return;
      }

      res.status(200).json(jsonData.products[index]);
    });
  });
});

// DELETE route - Allows to delete an item
// example: localhost:3000/clothes/1
app.delete("/clothes/:id", (req, res) => {
  const id = parseInt(req.params.id);

  fs.readFile("db.json", "utf8", (err, data) => {
    if (err) {
      console.log(err);
      res.status(500).send("Internal Server Error");
      return;
    }

    const jsonData = JSON.parse(data);

    const index = jsonData.products.findIndex((item) => item.id === id);

    if (index === -1) {
      res.status(404).send("Not Found");
      return;
    }

    jsonData.products.splice(index, 1);

    fs.writeFile("db.json", JSON.stringify(jsonData), (err) => {
      if (err) {
        console.log(err);
        res.status(500).send("Internal Server Error");
        return;
      }

      res.status(204).send();
    });
  });
});

app.get("/products/:id", (req, res) => {
  const productId = parseInt(req.params.id); // Convert id to integer

  fs.readFile("db.json", "utf8", (err, data) => {
    if (err) {
      console.log(err);
      res.status(500).send("Internal Server Error");
      return;
    }

    const jsonData = JSON.parse(data);
    const product = jsonData.products.find((item) => item.id === productId);

    if (!product) {
      res.status(404).send("Product not found");
      return;
    }

    res.json(product);
  });
});

// GET route for fetching shopping cart data
app.get("/cart", (req, res) => {
  fs.readFile("cart.json", "utf8", (err, data) => {
    if (err) {
      console.error("Error reading shopping cart file:", err);
      res.status(500).send("Internal Server Error");
      return;
    }

    // Parse JSON data
    const shoppingCartData = JSON.parse(data);
    res.json(shoppingCartData);
  });
});

app.listen(port, () => {
  console.log(`Server listening at http://localhost:${port}`);
});

//shopping cart requetes

// Define the route for the GET request to fetch the shopping cart data

// Start the server
