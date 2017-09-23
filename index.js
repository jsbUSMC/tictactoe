const mdpdf = require("markdown-pdf");

  mdpdf({highlightCssPath: "./node_modules/highlight.js/styles/atom-one-dark.css"}).from("./Writeup.md").to("./Writeup.pdf", function () {
    console.log("Done");
  });