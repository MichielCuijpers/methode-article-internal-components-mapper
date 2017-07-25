[![CircleCI](https://circleci.com/gh/Financial-Times/methode-article-internal-components-mapper.svg?style=svg)](https://circleci.com/gh/Financial-Times/methode-article-internal-components-mapper) [![Coverage Status](https://coveralls.io/repos/github/Financial-Times/methode-article-internal-components-mapper/badge.svg)](https://coveralls.io/github/Financial-Times/methode-article-internal-components-mapper)

# Methode Article Internal Components Mapper
Methode Article Internal Components Mapper is a Dropwizard application which consumes Kafka events and maps raw Methode articles to internal content components.
The transformed content components are put back to Kafka.

## Introduction
This application depends on the following micro-services:

* kafka-proxy;
* methode-article-mapper.

## Running

`java -jar target/methode-article-internal-components-mapper-1.0.0.jar server methode-article-internal-components-mapper.yaml`

## Endpoints

The transformation which takes place at each valid Kafka message can also be triggered by the `/map` endpoint.   

### API

The internal components model is documented by the description of this non-public `/map` API.

For Blueprint style documentation, see [here](api.md). 

API spec validation happens by `dredd`. It's linked with circleci, but to run it locally just type `dredd` in the project's top-level directory. 

### Posting content to be mapped

Transformation can be triggered through a POST message containing a Methode article to http://localhost:11070/map
In case the required transformation is triggered to provide an article preview, you need to set a `preview` query parameter in the URL with `true` as value: 
e.g., http://localhost:11070/map?preview=true

### Healthcheck and good-to-go

A GET request to http://localhost:11071/healthcheck or http://localhost:11070/__health

A GET request to http://localhost:11071/__gtg

Health and gtg are based on methode-article-mapper's health endpoint, and kafka topics' availabilities for reading and writing.

## Internals

For article validation the service leverages on methode article mapper's `/map` endpoint.

## Example of transformation output 
You can find an example of a transformed article below. 

```
{
  "design": {
    "theme": "basic"
  },
  "tableOfContents": {
    "sequence": "none",
    "labelType": "none"
  },
  "topper": {
    "headline": "Topper headline",
    "standfirst": "Topper standfirst",
    "backgroundColour": "auto",
    "layout": "split-text-left"
  },
  "leadImages": [
    {
      "id": "9aa35da4-df1b-11e6-bd9b-bb10c5678253",
      "type": "square"
    },
    {
      "id": "ae19a456-df1b-11e6-bd9b-bb10c5678253",
      "type": "standard"
    },
    {
      "id": "939f3f1e-df1b-11e6-bd9b-bb10c5678253",
      "type": "wide"
    }
  ],
  "unpublishedContentDescription": "<p>Content package coming next text</p>",
  "uuid": "e7f2eed0-ef92-11e6-abbc-ee7d9c5b3b90",
  "lastModified": "2017-03-16T13:51:52.976Z",
  "publishReference": "tid_xhwijstdot"
}
```
