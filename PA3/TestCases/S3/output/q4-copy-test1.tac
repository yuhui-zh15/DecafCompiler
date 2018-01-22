VTABLE(_Main) {
    <empty>
    Main
}

VTABLE(_animal) {
    <empty>
    animal
    _animal.setage;
    _animal.getage;
}

VTABLE(_people) {
    <empty>
    people
    _people.setaniage;
    _people.getage;
    _people.setage;
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T7 = 4
    parm _T7
    _T8 =  call _Alloc
    _T9 = VTBL <_Main>
    *(_T8 + 0) = _T9
    return _T8
}

FUNCTION(_animal_New) {
memo ''
_animal_New:
    _T10 = 8
    parm _T10
    _T11 =  call _Alloc
    _T12 = 0
    *(_T11 + 4) = _T12
    _T13 = VTBL <_animal>
    *(_T11 + 0) = _T13
    return _T11
}

FUNCTION(_people_New) {
memo ''
_people_New:
    _T14 = 20
    parm _T14
    _T15 =  call _Alloc
    _T16 = 0
    *(_T15 + 4) = _T16
    *(_T15 + 8) = _T16
    *(_T15 + 12) = _T16
    *(_T15 + 16) = _T16
    _T17 = VTBL <_people>
    *(_T15 + 0) = _T17
    return _T15
}

FUNCTION(main) {
memo ''
main:
    _T21 =  call _people_New
    _T19 = _T21
    parm _T19
    _T22 = *(_T19 + 0)
    _T23 = *(_T22 + 16)
    call _T23
    _T24 =  call _people_New
    _T25 = *(_T19 + 0)
    *(_T24 + 0) = _T25
    _T26 = *(_T19 + 4)
    *(_T24 + 4) = _T26
    _T27 = *(_T19 + 8)
    _T28 = *(_T27 + 4)
    _T29 = *(_T27 + 8)
    _T30 = 3
    _T31 = 0
    _T32 = (_T30 < _T31)
    if (_T32 == 0) branch _L17
    _T33 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T33
    call _PrintString
    call _Halt
_L17:
    _T34 = 4
    _T35 = (_T34 * _T30)
    _T36 = (_T34 + _T35)
    parm _T36
    _T37 =  call _Alloc
    *(_T37 + 0) = _T30
    _T38 = 0
    _T37 = (_T37 + _T36)
_L18:
    _T36 = (_T36 - _T34)
    if (_T36 == 0) branch _L19
    _T37 = (_T37 - _T34)
    *(_T37 + 0) = _T38
    branch _L18
_L19:
    *(_T37 + 4) = _T28
    *(_T37 + 8) = _T29
    *(_T24 + 8) = _T37
    _T39 = *(_T19 + 12)
    *(_T24 + 12) = _T39
    _T40 = *(_T19 + 16)
    *(_T24 + 16) = _T40
    _T20 = _T24
    _T41 = 99
    parm _T20
    parm _T41
    _T42 = *(_T20 + 0)
    _T43 = *(_T42 + 8)
    call _T43
    _T44 = "a: \n"
    parm _T44
    call _PrintString
    parm _T19
    _T45 = *(_T19 + 0)
    _T46 = *(_T45 + 12)
    call _T46
    _T47 = "b: \n"
    parm _T47
    call _PrintString
    parm _T20
    _T48 = *(_T20 + 0)
    _T49 = *(_T48 + 12)
    call _T49
}

FUNCTION(_animal.setage) {
memo '_T0:4 _T1:8'
_animal.setage:
    _T50 = *(_T0 + 4)
    *(_T0 + 4) = _T1
}

FUNCTION(_animal.getage) {
memo '_T2:4'
_animal.getage:
    _T51 = *(_T2 + 4)
    parm _T51
    call _PrintInt
    _T52 = "\n"
    parm _T52
    call _PrintString
}

FUNCTION(_people.setaniage) {
memo '_T3:4 _T4:8'
_people.setaniage:
    _T53 = *(_T3 + 12)
    parm _T53
    parm _T4
    _T54 = *(_T53 + 0)
    _T55 = *(_T54 + 8)
    call _T55
}

FUNCTION(_people.getage) {
memo '_T5:4'
_people.getage:
    _T56 = *(_T5 + 4)
    parm _T56
    call _PrintInt
    _T57 = "\n"
    parm _T57
    call _PrintString
    _T58 = *(_T5 + 8)
    _T59 = *(_T58 + 4)
    parm _T59
    call _PrintInt
    _T60 = "+"
    parm _T60
    call _PrintString
    _T61 = *(_T58 + 8)
    parm _T61
    call _PrintInt
    _T62 = "j"
    parm _T62
    call _PrintString
    _T63 = "\n"
    parm _T63
    call _PrintString
    _T64 = *(_T5 + 12)
    parm _T64
    _T65 = *(_T64 + 0)
    _T66 = *(_T65 + 12)
    call _T66
    _T67 = *(_T5 + 16)
    parm _T67
    call _PrintString
    _T68 = "\n"
    parm _T68
    call _PrintString
}

FUNCTION(_people.setage) {
memo '_T6:4'
_people.setage:
    _T69 = *(_T6 + 12)
    _T70 =  call _animal_New
    *(_T6 + 12) = _T70
    _T71 = 100
    parm _T6
    parm _T71
    _T72 = *(_T6 + 0)
    _T73 = VTBL <_people>
    _T74 = *(_T73 + 8)
    call _T74
    _T75 = *(_T6 + 4)
    _T76 = 10
    *(_T6 + 4) = _T76
    _T77 = *(_T6 + 16)
    _T78 = "11"
    *(_T6 + 16) = _T78
    _T79 = *(_T6 + 8)
    _T80 = 89
    _T81 = 3
    _T82 = 0
    _T83 = (_T81 < _T82)
    if (_T83 == 0) branch _L20
    _T84 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T84
    call _PrintString
    call _Halt
_L20:
    _T85 = 4
    _T86 = (_T85 * _T81)
    _T87 = (_T85 + _T86)
    parm _T87
    _T88 =  call _Alloc
    *(_T88 + 0) = _T81
    _T89 = 0
    _T88 = (_T88 + _T87)
_L21:
    _T87 = (_T87 - _T85)
    if (_T87 == 0) branch _L22
    _T88 = (_T88 - _T85)
    *(_T88 + 0) = _T89
    branch _L21
_L22:
    _T90 = 0
    *(_T88 + 4) = _T90
    _T91 = 8
    *(_T88 + 8) = _T91
    _T92 = 3
    _T93 = 0
    _T94 = (_T92 < _T93)
    if (_T94 == 0) branch _L23
    _T95 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T95
    call _PrintString
    call _Halt
_L23:
    _T96 = 4
    _T97 = (_T96 * _T92)
    _T98 = (_T96 + _T97)
    parm _T98
    _T99 =  call _Alloc
    *(_T99 + 0) = _T92
    _T100 = 0
    _T99 = (_T99 + _T98)
_L24:
    _T98 = (_T98 - _T96)
    if (_T98 == 0) branch _L25
    _T99 = (_T99 - _T96)
    *(_T99 + 0) = _T100
    branch _L24
_L25:
    _T101 = 3
    _T102 = 0
    _T103 = (_T101 < _T102)
    if (_T103 == 0) branch _L26
    _T104 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T104
    call _PrintString
    call _Halt
_L26:
    _T105 = 4
    _T106 = (_T105 * _T101)
    _T107 = (_T105 + _T106)
    parm _T107
    _T108 =  call _Alloc
    *(_T108 + 0) = _T101
    _T109 = 0
    _T108 = (_T108 + _T107)
_L27:
    _T107 = (_T107 - _T105)
    if (_T107 == 0) branch _L28
    _T108 = (_T108 - _T105)
    *(_T108 + 0) = _T109
    branch _L27
_L28:
    *(_T108 + 4) = _T80
    _T110 = 0
    *(_T108 + 8) = _T110
    _T111 = *(_T108 + 4)
    _T112 = *(_T108 + 8)
    _T113 = *(_T88 + 4)
    _T114 = *(_T88 + 8)
    _T115 = (_T111 + _T113)
    _T116 = (_T112 + _T114)
    *(_T99 + 4) = _T115
    *(_T99 + 8) = _T116
    *(_T6 + 8) = _T99
}

