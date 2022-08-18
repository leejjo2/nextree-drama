
### PersonalBook 등록
```json
{
  "addressBookCdo":{
    "name" : "Terry's AddressPage Book",
    "description" : "Terry's Shipping List",
    "addressBookId" : "1@1:1:1"
  }
}
```
```curl
curl --request POST \
  --url http://localhost:9093/feature/personalbook/register-personal-book/command \
  --header 'Content-Type: application/json' \
  --data '{
    "addressBookCdo":{
        "name" : "Terry'\''s AddressPage Book",
        "description" : "Terry'\''s Shipping List",
        "addressBookId" : "1@1:1:1"
    }
}'
```
### PersonalBook 조회
```json
{
  "addressBookId": "1@1:1:1"
}
```
```curl
curl --request POST \
  --url http://localhost:9093/feature/personalbook/find-personal-book/query \
  --header 'Content-Type: application/json' \
  --data '{
  "addressBookId": "1@1:1:1"
}'
```
### PersonalBook 수정
```json
{
  "addressBookId" : "1@1:1:1",
  "nameValueList" : {
    "nameValues" : [ {
      "name" : "name",
      "value" : "My Address Book"
    } ]
  }}
```
```bash
curl --request POST \
  --url http://localhost:9093/feature/personalbook/modify-personal-book/command \
  --header 'Content-Type: application/json' \
  --data '{
  "addressBookId" : "1@1:1:1",
  "nameValueList" : {
    "nameValues" : [ {
      "name" : "name",
      "value" : "My Address Book"
    } ]
  }}'
```

### PersonalPage 등록
```json
{
  "addressPageCdo" : {
    "name" : "Home",
    "address" : {
      "zipCode" : "12345",
      "zipAddress" : "서울시 금천구 디지털1로 155번지 잼잼빌딩",
      "city" : null,
      "state" : null,
      "street" : "703호",
      "country" : "대한민국"
    },
    "phoneNumber" : "+82 10-9999-9999",
    "addressBookId" : "1@1:1:1"
  }
}
```
```bash
curl --request POST \
  --url http://localhost:9093/feature/personalbook/register-personal-page/command \
  --header 'Content-Type: application/json' \
  --data '{
  "addressPageCdo" : {
    "name" : "Home",
    "address" : {
      "zipCode" : "12345",
      "zipAddress" : "서울시 금천구 디지털1로 155번지 잼잼빌딩",
      "city" : null,
      "state" : null,
      "street" : "703호",
      "country" : "대한민국"
    },
    "phoneNumber" : "+82 10-9999-9999",
    "addressBookId" : "1@1:1:1"
  }
}'
```
### PersonalPage 단건 조회
```json
{
  "addressPageId" : "3be46b1a-1aca-483c-b88b-e573fb5aa68b"
}
```
```bash
curl --request POST \
  --url http://localhost:9093/feature/personalbook/find-personal-page/query \
  --header 'Content-Type: application/json' \
  --data '{
  "addressPageId" : "3be46b1a-1aca-483c-b88b-e573fb5aa68b"
}'
```
### PersonalPage 수정
```json
{
  "addressPageId" : "3be46b1a-1aca-483c-b88b-e573fb5aa68b",
  "nameValueList" : {
    "nameValues" : [ {
      "name" : "name",
      "value" : "My Address Page"
    }, {
      "name" : "phoneNumber",
      "value" : "+82 2-1111-2222"
    } ]
  }
}
```
```bash
curl --request POST \
  --url http://localhost:9093/feature/personalbook/modify-personal-page/command \
  --header 'Content-Type: application/json' \
  --data '{
  "addressPageId" : "3be46b1a-1aca-483c-b88b-e573fb5aa68b",
  "nameValueList" : {
    "nameValues" : [ {
      "name" : "name",
      "value" : "My Address Page"
    }, {
      "name" : "phoneNumber",
      "value" : "+82 2-1111-2222"
    } ]
  }
}'
```
### PersonalPage 다건 조회
```json
{
  "offset" : {
		"offset": 0,
		"limit": 10
	},
  "addressBookId" : "1@1:1:1"
}
```
```bash
curl --request POST \
  --url http://localhost:9093/feature/personalbook/find-personal-pages-paged/query \
  --header 'Content-Type: application/json' \
  --data '{
  "offset" : {
		"offset": 0,
		"limit": 10
	},
  "addressBookId" : "1@1:1:1"
}'
```
### PersonalPage 기본 주소 등록
```json
{
  "addressBookId" : "1@1:1:1",
  "addressPageId" : "3be46b1a-1aca-483c-b88b-e573fb5aa68b"
}
```
```bash
curl --request POST \
  --url http://localhost:9093/feature/personalbook/assign-personal-base-address/command \
  --header 'Content-Type: application/json' \
  --data '{
	"addressBookId" : "1@1:1:1",
  "addressPageId" : "3be46b1a-1aca-483c-b88b-e573fb5aa68b"
}'
```
### PersonalPage -> TeamPage 전환
```json
{
  "teamAddressBookId" : "1:1:1:1",
  "addressPageId" : "3be46b1a-1aca-483c-b88b-e573fb5aa68b"
}
```
```bash
curl --request POST \
  --url http://localhost:9093/feature/personalbook/migrate-personal-page/command \
  --header 'Content-Type: application/json' \
  --data '{
  "teamAddressBookId" : "1:1:1:1",
  "addressPageId" : "3be46b1a-1aca-483c-b88b-e573fb5aa68b"
}'
```


------
### TeamBook 등록
```json
{
  "addressBookCdo" : {
    "name" : "Nextree AddressPage Book",
    "description" : "Nextree Team Address List",
    "addressBookId" : "1:1:1:1"
  }
}
```
```curl
curl --request POST \
  --url http://localhost:9093/feature/teambook/register-team-book/command \
  --header 'Content-Type: application/json' \
  --data '{
  "addressBookCdo" : {
    "name" : "Nextree AddressPage Book",
    "description" : "Nextree Team Address List",
    "addressBookId" : "1:1:1:1"
  }
}'
```
### TeamBook 조회
```json
{
  "addressBookId": "1:1:1:1"
}
```
```curl
curl --request POST \
  --url http://localhost:9093/feature/teambook/find-team-book/query \
  --header 'Content-Type: application/json' \
  --data '{
  "addressBookId": "1:1:1:1"
}'
```
### TeamBook 수정
```json
{
  "addressBookId": "1:1:1:1",
  "nameValueList": {
    "nameValues": [
      {
        "name": "name",
        "value": "Namoosori AddressPage Book"
      }
    ]
  }
}
```
```bash
curl --request POST \
  --url http://localhost:9093/feature/teambook/modify-team-book/command \
  --header 'Content-Type: application/json' \
  --data '{
  "addressBookId": "1:1:1:1",
  "nameValueList": {
    "nameValues": [
      {
        "name": "name",
        "value": "Namoosori AddressPage Book"
      }
    ]
  }
}'
```
### TeamPage 등록
```json
{
  "addressPageCdo" : {
    "name" : "Namoosori",
    "address" : {
      "zipCode" : "12345",
      "zipAddress" : "서울시 금천구 디지털1로 155번지 잼잼빌딩",
      "city" : null,
      "state" : null,
      "street" : "703호",
      "country" : "대한민국"
    },
    "phoneNumber" : "+82 10-9999-9999",
    "addressBookId" : "1:1:1:1"
  }
}
```
```bash
curl --request POST \
--url http://localhost:9093/feature/teambook/register-team-page/command \
--header 'Content-Type: application/json' \
--data '{
"addressPageCdo" : {
"name" : "Namoosori",
"address" : {
"zipCode" : "12345",
"zipAddress" : "서울시 금천구 디지털1로 155번지 잼잼빌딩",
"city" : null,
"state" : null,
"street" : "703호",
"country" : "대한민국"
},
"phoneNumber" : "+82 10-9999-9999",
"addressBookId" : "1:1:1:1"
}
}'
```
### TeamPage 단건 조회
```json
{
  "addressPageId" : "31994094-5dfc-4516-8b18-1e36feb67be0"
}
```
```bash
curl --request POST \
--url http://localhost:9093/feature/teambook/find-team-page/query \
--header 'Content-Type: application/json' \
--data '{
"addressPageId" : "31994094-5dfc-4516-8b18-1e36feb67be0"
}'
```
### TeamPage 수정
```json
{
  "addressPageId" : "31994094-5dfc-4516-8b18-1e36feb67be0",
  "nameValueList" : {
    "nameValues" : [ {
      "name" : "name",
      "value" : "Team Address Page"
    }, {
      "name" : "phoneNumber",
      "value" : "+82 10-1111-2222"
    } ]
  }
}
```
```bash
curl --request POST \
  --url http://localhost:9093/feature/teambook/modify-team-page/command \
  --header 'Content-Type: application/json' \
  --data '{
  "addressPageId" : "31994094-5dfc-4516-8b18-1e36feb67be0",
  "nameValueList" : {
    "nameValues" : [ {
      "name" : "name",
      "value" : "Team Address Page"
    }, {
      "name" : "phoneNumber",
      "value" : "+82 10-1111-2222"
    } ]
  }
}'
```

### TeamPage 다건 조회
```json
{
  "offset" : {
    "offset": 0,
    "limit": 10
  },
  "addressBookId" : "1:1:1:1"
}
```
```bash
curl --request POST \
--url http://localhost:9093/feature/teambook/find-team-pages-paged/query \
--header 'Content-Type: application/json' \
--data '{
"offset" : {
"offset": 0,
"limit": 10
},
"addressBookId" : "1:1:1:1"
}'
```
### TeamPage 기본 주소 등록
```json
{
  "addressBookId" : "1:1:1:1",
  "addressPageId" : "31994094-5dfc-4516-8b18-1e36feb67be0"
}
```
```bash
curl --request POST \
--url http://localhost:9093/feature/teambook/assign-team-page/command \
--header 'Content-Type: application/json' \
--data '{
"addressBookId" : "1:1:1:1",
"addressPageId" : "31994094-5dfc-4516-8b18-1e36feb67be0"
}'
```
### PersonalPage -> TeamPage 전환
```json
{
  "personalAddressBookId" : "1@1:1:1",
  "addressPageId" : "31994094-5dfc-4516-8b18-1e36feb67be0"
}
```
```bash
curl --request POST \
--url http://localhost:9093/feature/teambook/migrate-team-page/command \
--header 'Content-Type: application/json' \
--data '{
"personalAddressBookId" : "1@1:1:1",
"addressPageId" : "31994094-5dfc-4516-8b18-1e36feb67be0"
}'
```


