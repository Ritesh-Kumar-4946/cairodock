package com.main.genietalk.expandable;

import com.main.genietalk.R;

import java.util.Arrays;
import java.util.List;

public class GenreDataFactory {

  public static List<Genre> makeGenres() {
    return Arrays.asList(makeRockGenre(),
        makeJazzGenre(),
        makeClassicGenre());
  }

  public static List<MultiCheckGenre> makeMultiCheckGenres() {
    return Arrays.asList(makeMultiCheckRockGenre(),
        makeMultiCheckJazzGenre(),
        makeMultiCheckClassicGenre(),
        makeMultiCheckSalsaGenre(),
        makeMulitCheckBluegrassGenre());
  }

  public static List<SingleCheckGenre> makeSingleCheckGenres() {
    return Arrays.asList(makeSingleCheckRockGenre(),
        makeSingleCheckJazzGenre(),
        makeSingleCheckClassicGenre(),
        makeSingleCheckSalsaGenre(),
        makeSingleCheckBluegrassGenre());
  }

  public static Genre makeRockGenre() {
    return new Genre("Personal", makeRockArtists(), R.mipmap.ic_launcher);
  }

  public static MultiCheckGenre makeMultiCheckRockGenre() {
    return new MultiCheckGenre("Rock", makeRockArtists(),  R.mipmap.ic_launcher);
  }

  public static SingleCheckGenre makeSingleCheckRockGenre() {
    return new SingleCheckGenre("Rock", makeRockArtists(),  R.mipmap.ic_launcher);
  }

  public static List<Artist> makeRockArtists() {

    //<font color=#cc0029>First Color</font> <font color=#ffcc00>Second Color</font>
    Artist queen = new Artist(R.drawable.education,"Studied at Howard University", true,false,false);
    //Artist queen = new Artist(R.drawable.education,"<font color=#B6B6B6>Studied at</font> <font color=#ffffff>Howard university</font>", true,false,false);
    Artist styx = new Artist(R.drawable.location,"From New York, USA", true,false,false);
    //Artist styx = new Artist(R.drawable.locations,"<font color=#B6B6B6>From</font> <font color=#ffffff>New York, USA</font>", true,false,false);
    Artist reoSpeedwagon = new Artist(R.drawable.address,"Address 228, New York, NY 1003, USA", true,false,false);
    //Artist reoSpeedwagon = new Artist(R.drawable.address,"<font color=#B6B6B6>Address</font> <font color=#ffffff>228, New York, NY 1003, USA</font>", true,false,false);
    Artist boston = new Artist(R.drawable.mobileno,"Mobile No. +91-00-000-00000", true,false,false);
    //Artist boston = new Artist(R.drawable.phone,"<font color=#B6B6B6>Mobile No.</font> <font color=#ffffff>+91-00-000-00000</font>", true,false,false);
    Artist email = new Artist(R.drawable.profileemail,"Email ID test@mailinatore.com", true,false,false);
    //Artist email = new Artist(R.drawable.profileemail,"<font color=#B6B6B6>Email ID</font> <font color=#ffffff>test@mailinatore.com</font>", true,false,false);
    Artist visa = new Artist(R.drawable.creditcard,"Visa xx36(primary)", true,false,false);
    //Artist visa = new Artist(R.drawable.ic_card1,"<font color=#B6B6B6>Visa</font> <font color=#ffffff>xx36(primary)</font>", true,false,false);
    Artist master = new Artist(R.drawable.creditcard,"Master Card xx32(Secondary)", true,false,false);
    //Artist master = new Artist(R.drawable.ic_card1,"<font color=#B6B6B6>Master Card</font> <font color=#ffffff>xx32(Secondary)</font>", true,false,false);

    return Arrays.asList(queen, styx, reoSpeedwagon, boston,email,visa,master);

  }

  public static Genre makeJazzGenre() {
    return new Genre("Family Sharing", makeJazzArtists(),  R.mipmap.ic_launcher);
  }

  public static MultiCheckGenre makeMultiCheckJazzGenre() {
    return new MultiCheckGenre("Jazz", makeJazzArtists(),  R.mipmap.ic_launcher);
  }

  public static SingleCheckGenre makeSingleCheckJazzGenre() {
    return new SingleCheckGenre("Jazz", makeJazzArtists(),  R.mipmap.ic_launcher);
  }

  public static List<Artist> makeJazzArtists() {
    Artist milesDavis = new Artist(R.drawable.education,"Miles Davis", false,false,false);
    Artist ellaFitzgerald = new Artist(R.drawable.education,"Ella Fitzgerald", false,false,false);
    Artist billieHoliday = new Artist(R.drawable.education,"Billie Holiday", false,false,false);

    return Arrays.asList(milesDavis, ellaFitzgerald, billieHoliday);
  }

  public static Genre makeClassicGenre() {
    return new Genre("Bussiness Profile", makeClassicArtists(),  R.mipmap.ic_launcher);
  }

  public static MultiCheckGenre makeMultiCheckClassicGenre() {
    return new MultiCheckGenre("Classic", makeClassicArtists(),  R.mipmap.ic_launcher);
  }

  public static SingleCheckGenre makeSingleCheckClassicGenre() {
    return new SingleCheckGenre("Classic", makeClassicArtists(),  R.mipmap.ic_launcher);
  }

  public static List<Artist> makeClassicArtists() {
    Artist beethoven = new Artist(R.drawable.education,"GenieTalk Pvt. Ltd", false,false,true);
    Artist bach = new Artist(R.drawable.education,"A2doodh Pvt. Ltd", false,false,true);
    Artist brahms = new Artist(R.drawable.education,"Swarnim Ptv. Ltd", false,false,true);
    Artist puccini = new Artist(R.drawable.education,"", false,true,false);

    return Arrays.asList(beethoven, bach, brahms, puccini);
  }

  public static Genre makeSalsaGenre() {
    return new Genre("Salsa", makeSalsaArtists(),  R.mipmap.ic_launcher);
  }

  public static MultiCheckGenre makeMultiCheckSalsaGenre() {
    return new MultiCheckGenre("Salsa", makeSalsaArtists(),  R.mipmap.ic_launcher);
  }

  public static SingleCheckGenre makeSingleCheckSalsaGenre() {
    return new SingleCheckGenre("Salsa", makeSalsaArtists(),  R.mipmap.ic_launcher);
  }

  public static List<Artist> makeSalsaArtists() {
    Artist hectorLavoe = new Artist(R.drawable.education,"Hector Lavoe", true,false,false);
    Artist celiaCruz = new Artist(R.drawable.education,"Celia Cruz", false,false,false);
    Artist willieColon = new Artist(R.drawable.education,"Willie Colon", false,false,false);
    Artist marcAnthony = new Artist(R.drawable.education,"Marc Anthony", false,false,false);

    return Arrays.asList(hectorLavoe, celiaCruz, willieColon, marcAnthony);
  }

  public static Genre makeBluegrassGenre() {
    return new Genre("Bluegrass", makeBluegrassArtists(), R.drawable.ic_banjo);
  }

  public static MultiCheckGenre makeMulitCheckBluegrassGenre() {
    return new MultiCheckGenre("Bluegrass", makeBluegrassArtists(), R.drawable.ic_banjo);
  }

  public static SingleCheckGenre makeSingleCheckBluegrassGenre() {
    return new SingleCheckGenre("Bluegrass", makeBluegrassArtists(), R.drawable.ic_banjo);
  }

  public static List<Artist> makeBluegrassArtists() {
    Artist billMonroe = new Artist(R.drawable.education,"Bill Monroe", false,false,false);
    Artist earlScruggs = new Artist(R.drawable.education,"Earl Scruggs", false,false,false);
    Artist osborneBrothers = new Artist(R.drawable.education,"Osborne Brothers", true,false,false);
    Artist johnHartford = new Artist(R.drawable.education,"John Hartford", false,false,false);

    return Arrays.asList(billMonroe, earlScruggs, osborneBrothers, johnHartford);
  }

}

