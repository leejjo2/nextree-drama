class GeoCoordinate {
  latitude: string;
  longitude: string;

  constructor(latitude: string, longitude: string) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  static fromDomains(domains: GeoCoordinate[]): GeoCoordinate[] {
    return domains.map(domain => this.fromDomain(domain));
  }

  static fromDomain(domain: GeoCoordinate): GeoCoordinate {
    const geoCoordinate = new GeoCoordinate(
      domain.latitude,
      domain.longitude,
    );

    return geoCoordinate;
  }

  static new(): GeoCoordinate {
    return new GeoCoordinate('', '');
  }

}

export default GeoCoordinate;

