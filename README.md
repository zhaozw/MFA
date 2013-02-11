#MFA#
###API Reference###
___

###Login###

We're Working On Facebook and Twitter Intagration. Then We'll Use that for User Authentication. 

___

####User Methods####



**Load Basic Data** - `POST /Player_Info.php?FBID=###` 

_Returns:_
```
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
```


**Load Player's Hits** - `/Get_Hits.php?hits=###` 



_Returns:_
```
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
```
