export type Workspace = {
  id: string,
  name: string,
  type: WorkspaceType
};

export enum WorkspaceType {
  All = 'All',
  Pavilion = 'Pavilion',
  Cineroom = 'Cineroom',
}
