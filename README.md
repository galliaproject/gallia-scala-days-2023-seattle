# Scala Days 2023 (Seattle edition)

This repo contains:
- The slides for the presentation: ([PDF](./Anthony_Cros.Gallia.ScalaDays_2023_Seattle.pdf))
- The code used for the live demo, under [./src](./src)
- The [extra.tsv](./extra.tsv) file that is used in the demo
- __NOT INCLUDED__:  the Spark assembly jar under [./lib](./lib), required if you want to try to run the example with Spark RDDs (if you struggle to create one, contact me at contact.galliaproject@gmail.com)

To run the code as it is:
```shell
$ sbt # tested on 1.9.0
sbt> run
```
This should produce:

```json
{
  "donor": true,
  "donors": [
    {
      "name": "Alice",
      "age": 27,
      "phones": [
        "123-4598"
      ],
      "cars": [
        {
          "year": 18,
          "type": "rav4 BLACK"
        },
        {
          "year": 19,
          "type": "cruze GREEN"
        }
      ]
    },
    {
      "name": "Bob",
      "age": 26,
      "phones": [
        "123-4567",
        "123-9876"
      ],
      "cars": [
        {
          "year": 17,
          "type": "prius BLUE"
        }
      ]
    }
  ]
}
```

If you have any issues with running the above, don't hesitate to reach out to me on via [email](contact.galliaproject@gmail.com), [LinkedIn](https://www.linkedin.com/in/anthony-cros-3587b063/), [Twitter](https://twitter.com/anthony_cros), [Scala Users](https://users.scala-lang.org/t/introducing-gallia-a-library-for-data-manipulation/7112), or create a Github issue on  here!

