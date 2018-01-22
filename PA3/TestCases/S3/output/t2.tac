VTABLE(_Main) {
    <empty>
    Main
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T0 = 4
    parm _T0
    _T1 =  call _Alloc
    _T2 = VTBL <_Main>
    *(_T1 + 0) = _T2
    return _T1
}

FUNCTION(main) {
memo ''
main:
    _T6 = 3
    _T7 = 0
    _T8 = (_T6 < _T7)
    if (_T8 == 0) branch _L10
    _T9 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T9
    call _PrintString
    call _Halt
_L10:
    _T10 = 4
    _T11 = (_T10 * _T6)
    _T12 = (_T10 + _T11)
    parm _T12
    _T13 =  call _Alloc
    *(_T13 + 0) = _T6
    _T14 = 0
    _T13 = (_T13 + _T12)
_L11:
    _T12 = (_T12 - _T10)
    if (_T12 == 0) branch _L12
    _T13 = (_T13 - _T10)
    *(_T13 + 0) = _T14
    branch _L11
_L12:
    _T15 = 1
    _T5 = _T15
    _T16 = "wow!"
    _T4 = _T16
    _T17 = 3
    _T3 = _T17
    _T18 = 1
    _T19 = 3
    _T20 = 0
    _T21 = (_T19 < _T20)
    if (_T21 == 0) branch _L13
    _T22 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T22
    call _PrintString
    call _Halt
_L13:
    _T23 = 4
    _T24 = (_T23 * _T19)
    _T25 = (_T23 + _T24)
    parm _T25
    _T26 =  call _Alloc
    *(_T26 + 0) = _T19
    _T27 = 0
    _T26 = (_T26 + _T25)
_L14:
    _T25 = (_T25 - _T23)
    if (_T25 == 0) branch _L15
    _T26 = (_T26 - _T23)
    *(_T26 + 0) = _T27
    branch _L14
_L15:
    _T28 = 0
    *(_T26 + 4) = _T28
    _T29 = 3
    *(_T26 + 8) = _T29
    _T30 = 3
    _T31 = 0
    _T32 = (_T30 < _T31)
    if (_T32 == 0) branch _L16
    _T33 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T33
    call _PrintString
    call _Halt
_L16:
    _T34 = 4
    _T35 = (_T34 * _T30)
    _T36 = (_T34 + _T35)
    parm _T36
    _T37 =  call _Alloc
    *(_T37 + 0) = _T30
    _T38 = 0
    _T37 = (_T37 + _T36)
_L17:
    _T36 = (_T36 - _T34)
    if (_T36 == 0) branch _L18
    _T37 = (_T37 - _T34)
    *(_T37 + 0) = _T38
    branch _L17
_L18:
    _T39 = 3
    _T40 = 0
    _T41 = (_T39 < _T40)
    if (_T41 == 0) branch _L19
    _T42 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T42
    call _PrintString
    call _Halt
_L19:
    _T43 = 4
    _T44 = (_T43 * _T39)
    _T45 = (_T43 + _T44)
    parm _T45
    _T46 =  call _Alloc
    *(_T46 + 0) = _T39
    _T47 = 0
    _T46 = (_T46 + _T45)
_L20:
    _T45 = (_T45 - _T43)
    if (_T45 == 0) branch _L21
    _T46 = (_T46 - _T43)
    *(_T46 + 0) = _T47
    branch _L20
_L21:
    *(_T46 + 4) = _T18
    _T48 = 0
    *(_T46 + 8) = _T48
    _T49 = *(_T46 + 4)
    _T50 = *(_T46 + 8)
    _T51 = *(_T26 + 4)
    _T52 = *(_T26 + 8)
    _T53 = (_T49 + _T51)
    _T54 = (_T50 + _T52)
    *(_T37 + 4) = _T53
    *(_T37 + 8) = _T54
    _T55 = *(_T37 + 4)
    _T56 = *(_T37 + 8)
    *(_T13 + 4) = _T55
    *(_T13 + 8) = _T56
    if (_T5 == 0) branch _L22
    _T57 = 5
    _T58 = (_T3 * _T57)
    _T3 = _T58
_L22:
    parm _T5
    call _PrintBool
    _T59 = " "
    parm _T59
    call _PrintString
    parm _T3
    call _PrintInt
}

