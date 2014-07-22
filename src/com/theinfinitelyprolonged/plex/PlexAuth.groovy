/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.bysh.plex

import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

class PlexAuth {
  def plexSite = 'https://plex.tv/'
  def user
  def pass

  HTTPBuilder plexAuthSite = new HTTPBuilder( plexSite )
  
  PlexAuth(plexusername, plexpassword) {
    user = plexusername
    pass = plexpassword
  }
  String getToken() {
    def data = plexAuthSite.request( POST ) {
      uri.path = 'users/sign_in.xml'
      headers = [
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36',
        'Authorization': "Basic " + "$user:$pass".getBytes('iso-8859-1').encodeBase64(),
        'X-Plex-Device': 'Windows',
        'X-Plex-Device-Name': 'Bysh Authorizer (Cmd)',
        'X-Plex-Platform': 'Groovy',
        'X-Plex-version': '2.3.3',
        'X-Plex-Product': 'Bysh Authorizer',
        'X-Plex-Client-Identifier': UUID.randomUUID() 
      ]
    }
    
   return data."authentication-token";
  }
}
