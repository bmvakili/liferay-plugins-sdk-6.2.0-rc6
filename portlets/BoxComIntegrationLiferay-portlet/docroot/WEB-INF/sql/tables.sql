create table BoxToken (
	boxTokenId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	accessToken VARCHAR(75) null,
	refreshToken VARCHAR(75) null,
	accessTokenExpiration LONG,
	refreshTokenExpiration LONG,
	callbackURL TEXT null,
	expired BOOLEAN,
	repositoryId LONG
);