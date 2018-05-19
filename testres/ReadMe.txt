/**
   FileCharsetDetector is designed by MX He
   that help to detetect possible CharsetNames or Encodings of a text file.
  
   It comtains only one class FileCharsetDetector.java
   and a lib chardet.jar from: 
   https://sourceforge.net/projects/jchardet/
  
   The Usage :
   java FileCharsetDetector <path>
  
   The runing results:
  
   ..>java FileCharsetDetector ./test1_utf8_noBOM.txt
   Probable Charset = UTF-8 : ÖĞÎÄ²âÊÔ
   Probable Charset = Shift_JIS : ÇW?Öœ?Øi¾Á??
   Probable Charset = GB18030 : ä¸­æ–‡æµ‹è¯•
   Probable Charset = UTF-16LE : ?¦GÎ”??é„
   Probable Charset = windows-1252 : ????¨C????¨¨??
   Probable Charset = UTF-16BE : şñ?ê¦OÚ»?
   
   ..>java FileCharsetDetector ./test2_Default.txt
   Probable Charset = UTF-8 : ?ÖĞÎÄ²âÊÔ
   Probable Charset = UTF-16LE : ?şø?ë|¥‚Úª??
   Probable Charset = GB18030 : ï»¿ä¸­æ–‡æµ‹è¯?
   Probable Charset = UTF-16BE : ???¥‘Ïn???
   Probable Charset = windows-1252 : ???????¨C????¨¨??
   
   ..>java FileCharsetDetector ./test3_utf8.txt
   Probable Charset = UTF-8 : ÖĞÎÄ²âÊÔ
   Probable Charset = Shift_JIS : ÇW?Öœ?Øi¾Á??
   Probable Charset = GB18030 : ä¸­æ–‡æµ‹è¯•
   Probable Charset = UTF-16LE : ?¦GÎ”??é„
   Probable Charset = windows-1252 : ????¨C????¨¨??
   Probable Charset = UTF-16BE : şñ?ê¦OÚ»?
   
   ..>java FileCharsetDetector ./test4_ASCII_Escap.txt
   CHARSET = ASCII : \u4E2D\u6587\u6D4B\u8BD5
   
   ..>java FileCharsetDetector ./test5_ASCII.txt
   Probable Charset = GB18030 : ÖĞÎÄ²âÊÔ
   Probable Charset = Shift_JIS : ?????º|?
   Probable Charset = EUC-JP : äªdÏ¼¾
   Probable Charset = UTF-16LE : ??ùÁ?
   Probable Charset = EUC-KR : ™©¿ï?—U
   Probable Charset = UTF-16BE : ????
   Probable Charset = Big5 : ¸œ`ñö³
   Probable Charset = GB2312 : ÖĞÎÄ²âÊÔ

*/
