import {dock as dock} from './dock';

export const devauth = {
  username: 'demo@naraway.io',
  password: '0000',
  pavilionId: '1:1:1',

  loggedIn: true,
  cineroomIds: ['1:1:1:1', '1:1:1:2', '1:1:1:3'],
  citizenSessionId: 'db851f94-eb25-454c-85fb-7f84761b662d',
  citizen: {
    pavilionId: '1:1:1',
    loginId: 'demo@naraway.io',
    email: 'demo@naraway.io',
    displayName: 'Nara User',
    additionalInformation: {
      citizenUserId: 'c6b8d53b-1102-4a3c-8ed6-347213983b02',
      citizenSessionId: 'db851f94-eb25-454c-85fb-7f84761b662d',
    },
  },
  token: {
    access: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dpbklkIjoiYWRtaW5AbmV4dHJlZS5pbyIsInBhdmlsaW9uSWQiOiIxOjE6MSIsInVzZXJfbmFtZSI6ImFkbWluQG5leHRyZWUuaW8iLCJkaXNwbGF5TmFtZSI6IktpbSBOYXJhIiwic2NvcGUiOlsiY2l0aXplbiJdLCJjaXRpemVuVXNlcklkIjoiZWNkYjdmNjQtMjRkOC00OTNmLThhNDYtNmYxYWRjNTczNTljIiwiY2l0aXplblNlc3Npb25JZCI6ImViNjE0OGI5LTA2YTEtNGY4MS04Y2E2LTVkNWJhNjNjNjNlZCIsImV4cCI6MTY5MTIyMTMzNywianRpIjoiYUJpYkx5emlXTnVpcVhjc2xfRDJZZ0x4a044IiwiZW1haWwiOiJhZG1pbkBuZXh0cmVlLmlvIiwiY2luZXJvb21JZHMiOlsiMToxOjE6MSJdLCJjbGllbnRfaWQiOiJuYXJhIn0.Z7O21ptPTClsfPs4-gZaoauU8UFWe3r_ZcJ1SH3UT0Y',
    refresh: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dpbklkIjoiYWRtaW5AbmV4dHJlZS5pbyIsInVzZXJfbmFtZSI6ImFkbWluQG5leHRyZWUuaW8iLCJkaXNwbGF5TmFtZSI6IktpbSBOYXJhIiwiY2l0aXplblNlc3Npb25JZCI6IjVmNTE5OTdiLWM3NWMtNGM2MC1iOWNjLThhMGI4NjYyMDdhNyIsImNpbmVyb29tSWRzIjpbIjE6MToxOjEiXSwiY2xpZW50X2lkIjoibmFyYSIsInBhdmlsaW9uSWQiOiIxOjE6MSIsInNjb3BlIjpbImNpdGl6ZW4iXSwiY2l0aXplblVzZXJJZCI6ImIxZGY2Y2VkLTYzNmUtNDU5NS05MGU2LTZlN2FlZjc0YWI0OCIsImF0aSI6Im8zRXl0SnlPTDdSanFKbFhjX1dBdlpKdGdPSSIsImV4cCI6MTY2MjI1NTU2MCwianRpIjoiUGE1Z1BFcUdkdVplQU5SRy05VFJpRDZfeEF3IiwiZW1haWwiOiJhZG1pbkBuZXh0cmVlLmlvIn0.HtGjd_xiB_rv407_UzaFxClHSQ5qVibbwUB_FHFJHo4',
  },
};

export const devdock = {
  activeDock: dock,
  activePavilion: dock.pavilion,
  activeCitizen: dock.citizen,
  activeCineroom: dock.cinerooms[0].cineroom,
  activeAudience: dock.cinerooms[0].audience,
  activeStage: dock.cinerooms[0].stages[0].stage,
  activeActor: dock.cinerooms[0].stages[0].actor,
  activeKollection: dock.cinerooms[0].stages[0].kollections[0].kollection,
  activeKollectionRoles: dock.cinerooms[0].stages[0].kollections[0].kollectionRoles.map(role => role.code),
  activeDramaRoles: dock.cinerooms[0].stages[0].kollections[0].kollectionRoles[0].dramaRoles.map(role => role.code),
};

export const devinterceptors = [{
  request: {
    onFulfilled: (config) => {
      const roles = ['personal-user', 'team-admin', 'team-user'].join(',');
      config.headers = {...(config.headers || {}), roles};
      return config;
    },
  },
}];
