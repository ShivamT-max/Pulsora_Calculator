Sleep(5000)
ControlFocus("Open","","Edit1")
Sleep(2000)
ControlSend("Open","","Edit1",$CmdLine[1])
Sleep(5000)
ControlClick("Open","","Button1")
Sleep(2000)