import { axiosApi } from '@nara/prologue';

export enum LoopbackType {
  Failover,
  Forced,
}

// export const configureLoopback = (mode: LoopbackType, ...object: object[]) => axiosApi.interceptors.response.use(
//   (response) => {
//     if (mode === LoopbackType.Forced) {
//       return issueGliderMock[response.config.url];
//     }
//     return response;
//   },
//   (error) => {
//
//     error = issueGliderMock[error.config.url];
//
//     return error;
//   },
// );

export const issueGliderMock: object = {
  '/api/address-book/feature/personalbook/find-personal-pages-paged/query': {
    'data': {
      'queryResult': [
        {
          'id': '4edfc61a-6380-47c2-9cc2-4a2e9d0a60d4',
          'entityVersion': 0,
          'registrationTime': 1654946617965,
          'modificationTime': 1656488965505,
          'requesterPavilionId': '1:1:1',
          'requesterCineroomId': '1:1:1:1',
          'requesterCitizenId': '1@1:1:1',
          'requesterStageId': '1:1:1:1-1',
          'requesterActorId': '1@1:1:1:1-1',
          'name': 'personal page%^%^%',
          'address': {
            'zipCode': 'string',
            'zipAddress': 'string!',
            'city': 'string',
            'state': 'string',
            'street': 'string',
            'country': 'string',
          },
          'phoneNumber': 'string',
          'fields': [],
          'baseAddress': false,
          'memo': 'null',
          'addressBookId': '1@1:1:1',
          'citizenKey': {
            'id': '1@1:1:1',
            'type': 'Citizen',
          },
          'cineroomKey': {
            'id': '1:1:1:1',
            'type': 'Cineroom',
          },
        },
        {
          'id': '534fd51f-e0ce-4c42-af40-19a38e6cd276',
          'entityVersion': 0,
          'registrationTime': 1654946622083,
          'modificationTime': 1656485131348,
          'requesterPavilionId': '1:1:1',
          'requesterCineroomId': '1:1:1:1',
          'requesterCitizenId': '1@1:1:1',
          'requesterStageId': '1:1:1:1-1',
          'requesterActorId': '1@1:1:1:1-1',
          'name': 'personal page',
          'address': {
            'zipCode': 'string',
            'zipAddress': 'string',
            'city': 'string',
            'state': 'string',
            'street': 'string',
            'country': 'string',
          },
          'phoneNumber': 'string',
          'baseAddress': false,
          'addressBookId': '1@1:1:1',
          'citizenKey': {
            'id': '1@1:1:1',
            'type': 'Citizen',
          },
          'cineroomKey': {
            'id': '1:1:1:1',
            'type': 'Cineroom',
          },
        },
        {
          'id': '9fc4be3b-cb13-4510-a613-cc4dfe7e3333',
          'entityVersion': 0,
          'registrationTime': 1654947898149,
          'modificationTime': 1656468233462,
          'requesterPavilionId': '1:1:1',
          'requesterCineroomId': '1:1:1:1',
          'requesterCitizenId': '1@1:1:1',
          'requesterStageId': '1:1:1:1-1',
          'requesterActorId': '1@1:1:1:1-1',
          'name': 'string',
          'address': {
            'zipCode': '08110',
            'zipAddress': 'string',
            'city': '서울',
            'state': 'string',
            'street': '가산로',
            'country': '대한민국',
          },
          'phoneNumber': 'string',
          'baseAddress': false,
          'addressBookId': '1@1:1:1',
          'citizenKey': {
            'id': '1@1:1:1',
            'type': 'Citizen',
          },
          'cineroomKey': {
            'id': '1:1:1:1',
            'type': 'Cineroom',
          },
        },
        {
          'id': '519edd0e-5b4e-4e4a-8063-0749a0f877e6',
          'entityVersion': 0,
          'registrationTime': 1655110246659,
          'modificationTime': 1655283503216,
          'requesterPavilionId': '1:1:1',
          'requesterCineroomId': '1:1:1:1',
          'requesterCitizenId': '1@1:1:1',
          'requesterStageId': '1:1:1:1-1',
          'requesterActorId': '1@1:1:1:1-1',
          'name': '회사',
          'address': {
            'zipCode': '139091',
            'zipAddress': '1805호',
            'city': '서울특별시',
            'state': '금천구',
            'street': '가산동',
            'country': '',
          },
          'phoneNumber': '020000000',
          'baseAddress': false,
          'addressBookId': '1@1:1:1',
          'citizenKey': {
            'id': '1@1:1:1',
            'type': 'Citizen',
          },
          'cineroomKey': {
            'id': '1:1:1:1',
            'type': 'Cineroom',
          },
        },
        {
          'id': '3be46b1a-1aca-483c-b88b-e573fb5aa68b',
          'entityVersion': 0,
          'registrationTime': 1655187431704,
          'modificationTime': 1656468229195,
          'requesterPavilionId': '1:1:1',
          'requesterCineroomId': '1:1:1:1',
          'requesterCitizenId': '1@1:1:1',
          'requesterStageId': '1:1:1:1-1',
          'requesterActorId': '1@1:1:1:1-1',
          'name': 'My Address Page',
          'address': {
            'zipCode': '12345',
            'zipAddress': '서울시 금천구 디지털1로 155번지 잼잼빌딩',
            'street': '703호',
            'country': '대한민국',
          },
          'phoneNumber': '+82 2-1111-2222',
          'baseAddress': false,
          'addressBookId': '1@1:1:1',
          'citizenKey': {
            'id': '1@1:1:1',
            'type': 'Citizen',
          },
          'cineroomKey': {
            'id': '1:1:1:1',
            'type': 'Cineroom',
          },
        },
        {
          'id': '51f37ded-8994-4ae0-8248-5b36f5153a81',
          'entityVersion': 0,
          'registrationTime': 1655279328895,
          'modificationTime': 1656468230546,
          'requesterPavilionId': '1:1:1',
          'requesterCineroomId': '1:1:1:3',
          'requesterCitizenId': '1@1:1:1',
          'requesterStageId': '1:1:1:3-1',
          'requesterActorId': '1@1:1:1:3-1',
          'name': '',
          'address': {
            'zipCode': '',
            'zipAddress': '',
            'city': '',
            'state': '',
            'street': '',
            'country': '',
          },
          'phoneNumber': '',
          'baseAddress': false,
          'addressBookId': '1@1:1:1',
          'citizenKey': {
            'id': '1@1:1:1',
            'type': 'Citizen',
          },
          'cineroomKey': {
            'id': '1:1:1:3',
            'type': 'Cineroom',
          },
        },
        {
          'id': '9a7bd92d-03ca-4871-9b18-ff78d6a979f9',
          'entityVersion': 0,
          'registrationTime': 1656411413159,
          'modificationTime': 1656468231951,
          'requesterPavilionId': '1:1:1',
          'requesterCineroomId': '1:1:1:1',
          'requesterCitizenId': '1@1:1:1',
          'requesterStageId': '1:1:1:1-1',
          'requesterActorId': '1@1:1:1:1-1',
          'name': 't',
          'address': {
            'zipCode': 'a',
            'zipAddress': 'e',
            'city': 'b',
            'state': 'c',
            'street': 'd',
            'country': '',
          },
          'phoneNumber': '1',
          'baseAddress': false,
          'addressBookId': '1@1:1:1',
          'citizenKey': {
            'id': '1@1:1:1',
            'type': 'Citizen',
          },
          'cineroomKey': {
            'id': '1:1:1:1',
            'type': 'Cineroom',
          },
        },
      ],
      'offset': {
        'offset': 0,
        'limit': 12,
        'totalCount': 7,
        'totalCountRequested': true,
        'sortingField': 'registrationTime',
        'sortDirection': 'ASCENDING',
      },
      'requestFailed': false,
    },
  },
};
