#!/bin/sh

cd `dirname ${0}`
source ./env.sh

curl --request POST \
--url http://${host}/feature/personalbook/register-personal-book/command \
--header 'Content-Type: application/json' \
--data '
{
  "addressBookCdo" : {
    "requesterKey" : {
      "id" : "1@1:1:1:1-1",
      "type" : "Actor"
    },
    "name" : "Terry AddressPage Book",
    "description" : "Terry Shipping List",
    "addressBookId" : "1@1:1:1"
  }
}
'
