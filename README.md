Getting to philosophy
DB User: Cloud MongoDB
Hosting: on AWS, url to be used bento-env.6bjpqmfzqy.us-west-2.elasticbeanstalk.com
Path to take = /rest/wiki/find
Params used = depth => indicates how many hops to go deep to find Philosophy

HTTP POST
http://bento-env.6bjpqmfzqy.us-west-2.elasticbeanstalk.com/rest/wiki/find?depth=100
{
 "requestURL": "https://en.wikipedia.org/wiki/Amazon_Web_Services"
}
Response:

{
    "request": "Amazon_Web_Services",
    "hops": [
        "/wiki/Amazon.com",
        "/wiki/Doing_business_as",
        "/wiki/Pseudonym",
        "/wiki/Name",
        "/wiki/Term_(language)",
        "/wiki/Word",
        "/wiki/Linguistics",
        "/wiki/Language",
        "/wiki/Communication",
        "/wiki/Meaning_(semiotics)",
        "/wiki/Semiotics",
        "/wiki/Meaning-making",
        "/wiki/Psychology",
        "/wiki/Science",
        "/wiki/Knowledge",
        "/wiki/Fact",
        "/wiki/Reality",
        "/wiki/Object_of_the_mind",
        "/wiki/Philosophy"
    ],
    "result": "Found Philosophy article in 19 links.\n",
    "code": 200
}
