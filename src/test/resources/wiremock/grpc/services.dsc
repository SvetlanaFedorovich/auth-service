
�
keycloak.proto"�
TokenRequest
username (	Rusername
password (	Rpassword
	client_id (	RclientId

grant_type (	R	grantType"�
TokenResponse!
access_token (	RaccessToken#
refresh_token (	RrefreshToken

token_type (	R	tokenType#
session_state (	RsessionState

expires_in (R	expiresIn2>
KeycloakService+
getToken.TokenRequest.TokenResponse" B
mvp.project.authservicePbproto3
�
redis.proto",
SessionRequest
username (	Rusername"9
CookieRequest
name (	Rname
value (	Rvalue"(
CookieResponse
result (	Rresult2v
RedisService2
saveSessionID.CookieRequest.CookieResponse" 2
getSessionID.SessionRequest.CookieResponse" B
mvp.project.authservicePbproto3