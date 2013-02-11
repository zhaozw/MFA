MFA
API Reference

Login

Currently there really is no login process. 

Querying

application/json


User Methods

Load Basic Infomation:

----  POST  ---- 

/Player_Info.php?FBID=###

Returns:

{
  Game: [
  {
    FBID: "###",
    First_Name: "###",
    Last_Name: "###",
    Highscore: "###",
    Money: "###",
    Hits: "###"
  }
      ]
}

Get Hit Infomation:

---  POST  ----

/Get_Hits.php?hits=###

Returns:

Hits: [
        {
          HitsID: "james0123",
          Hit0Bool: "1",
          Hit0From: "christian",
          Hit0Msg: "haha fucker",
          Hit1Bool: "0",
          Hit1From: "NULL",
          Hit1Msg: "NULL"
        }
      ]
