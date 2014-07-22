package com.theinfinitelyprolonged;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import me.bysh.plex.PlexAuth;
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 



public class Main {
  static public class Arguments
  {
    @Parameter(names = "-server")
    private String server = "<server>";
    @Parameter(names = "-port")
    private String port = "<port>";
    @Parameter(names = "-user", required = true, description = "Plexpass username")
    private String name;
    @Parameter(names = "-pass", required = true, description = "Plexpass password")
    private String pass;

  }
  
  private static Log log = LogFactory.getLog(Main.class);
  public static void main(String[] args)
  {
    Arguments arg = new Arguments();
    new JCommander(arg, args);
    
    log.debug("Fetching token.");
    PlexAuth auth = new PlexAuth(arg.name, arg.pass);
    String token = auth.getToken();
    log.info("User Token: " + token);
    log.info("Try a few of these URIs:");
    log.info(String.format("http://%s:%s/library/sections/all/refresh?X-Plex-Token=%s", arg.server, arg.port, token));
    log.info(String.format("http://%s:%s/myplex/account?X-Plex-Token=%s", arg.server, arg.port, token));
  }
}
