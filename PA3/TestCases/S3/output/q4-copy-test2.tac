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
    _T20 =  call _people_New
    _T18 = _T20
    parm _T18
    _T21 = *(_T18 + 0)
    _T22 = *(_T21 + 16)
    call _T22
    _T23 =  call _people_New
    _T24 = *(_T18 + 0)
    *(_T23 + 0) = _T24
    _T25 = *(_T18 + 4)
    *(_T23 + 4) = _T25
    _T26 = *(_T18 + 8)
    _T27 = *(_T26 + 4)
    _T28 = *(_T26 + 8)
    _T29 = 3
    _T30 = 0
    _T31 = (_T29 < _T30)
    if (_T31 == 0) branch _L17
    _T32 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T32
    call _PrintString
    call _Halt
_L17:
    _T33 = 4
    _T34 = (_T33 * _T29)
    _T35 = (_T33 + _T34)
    parm _T35
    _T36 =  call _Alloc
    *(_T36 + 0) = _T29
    _T37 = 0
    _T36 = (_T36 + _T35)
_L18:
    _T35 = (_T35 - _T33)
    if (_T35 == 0) branch _L19
    _T36 = (_T36 - _T33)
    *(_T36 + 0) = _T37
    branch _L18
_L19:
    *(_T36 + 4) = _T27
    *(_T36 + 8) = _T28
    *(_T23 + 8) = _T36
    _T38 = *(_T18 + 12)
    _T39 =  call _animal_New
    _T40 = *(_T38 + 0)
    *(_T39 + 0) = _T40
    _T41 = *(_T38 + 4)
    *(_T39 + 4) = _T41
    *(_T23 + 12) = _T39
    _T42 = *(_T18 + 16)
    *(_T23 + 16) = _T42
    _T19 = _T23
    _T43 = 99
    parm _T19
    parm _T43
    _T44 = *(_T19 + 0)
    _T45 = *(_T44 + 8)
    call _T45
    _T46 = "a: \n"
    parm _T46
    call _PrintString
    parm _T18
    _T47 = *(_T18 + 0)
    _T48 = *(_T47 + 12)
    call _T48
    _T49 = "b: \n"
    parm _T49
    call _PrintString
    parm _T19
    _T50 = *(_T19 + 0)
    _T51 = *(_T50 + 12)
    call _T51
}

FUNCTION(_animal.setage) {
memo '_T0:4 _T1:8'
_animal.setage:
    _T52 = *(_T0 + 4)
    *(_T0 + 4) = _T1
}

FUNCTION(_animal.getage) {
memo '_T2:4'
_animal.getage:
    _T53 = *(_T2 + 4)
    parm _T53
    call _PrintInt
    _T54 = "\n"
    parm _T54
    call _PrintString
}

FUNCTION(_people.setaniage) {
memo '_T3:4 _T4:8'
_people.setaniage:
    _T55 = *(_T3 + 12)
    parm _T55
    parm _T4
    _T56 = *(_T55 + 0)
    _T57 = *(_T56 + 8)
    call _T57
}

FUNCTION(_people.getage) {
memo '_T5:4'
_people.getage:
    _T58 = *(_T5 + 4)
    parm _T58
    call _PrintInt
    _T59 = "\n"
    parm _T59
    call _PrintString
    _T60 = *(_T5 + 8)
    _T61 = *(_T60 + 4)
    parm _T61
    call _PrintInt
    _T62 = "+"
    parm _T62
    call _PrintString
    _T63 = *(_T60 + 8)
    parm _T63
    call _PrintInt
    _T64 = "j"
    parm _T64
    call _PrintString
    _T65 = "\n"
    parm _T65
    call _PrintString
    _T66 = *(_T5 + 12)
    parm _T66
    _T67 = *(_T66 + 0)
    _T68 = *(_T67 + 12)
    call _T68
    _T69 = *(_T5 + 16)
    parm _T69
    call _PrintString
    _T70 = "\n"
    parm _T70
    call _PrintString
}

FUNCTION(_people.setage) {
memo '_T6:4'
_people.setage:
    _T71 = *(_T6 + 12)
    _T72 =  call _animal_New
    *(_T6 + 12) = _T72
    _T73 = 100
    parm _T6
    parm _T73
    _T74 = *(_T6 + 0)
    _T75 = VTBL <_people>
    _T76 = *(_T75 + 8)
    call _T76
    _T77 = *(_T6 + 4)
    _T78 = 10
    *(_T6 + 4) = _T78
    _T79 = *(_T6 + 16)
    _T80 = "11"
    *(_T6 + 16) = _T80
    _T81 = *(_T6 + 8)
    _T82 = 89
    _T83 = 3
    _T84 = 0
    _T85 = (_T83 < _T84)
    if (_T85 == 0) branch _L20
    _T86 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T86
    call _PrintString
    call _Halt
_L20:
    _T87 = 4
    _T88 = (_T87 * _T83)
    _T89 = (_T87 + _T88)
    parm _T89
    _T90 =  call _Alloc
    *(_T90 + 0) = _T83
    _T91 = 0
    _T90 = (_T90 + _T89)
_L21:
    _T89 = (_T89 - _T87)
    if (_T89 == 0) branch _L22
    _T90 = (_T90 - _T87)
    *(_T90 + 0) = _T91
    branch _L21
_L22:
    _T92 = 0
    *(_T90 + 4) = _T92
    _T93 = 8
    *(_T90 + 8) = _T93
    _T94 = 3
    _T95 = 0
    _T96 = (_T94 < _T95)
    if (_T96 == 0) branch _L23
    _T97 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T97
    call _PrintString
    call _Halt
_L23:
    _T98 = 4
    _T99 = (_T98 * _T94)
    _T100 = (_T98 + _T99)
    parm _T100
    _T101 =  call _Alloc
    *(_T101 + 0) = _T94
    _T102 = 0
    _T101 = (_T101 + _T100)
_L24:
    _T100 = (_T100 - _T98)
    if (_T100 == 0) branch _L25
    _T101 = (_T101 - _T98)
    *(_T101 + 0) = _T102
    branch _L24
_L25:
    _T103 = 3
    _T104 = 0
    _T105 = (_T103 < _T104)
    if (_T105 == 0) branch _L26
    _T106 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T106
    call _PrintString
    call _Halt
_L26:
    _T107 = 4
    _T108 = (_T107 * _T103)
    _T109 = (_T107 + _T108)
    parm _T109
    _T110 =  call _Alloc
    *(_T110 + 0) = _T103
    _T111 = 0
    _T110 = (_T110 + _T109)
_L27:
    _T109 = (_T109 - _T107)
    if (_T109 == 0) branch _L28
    _T110 = (_T110 - _T107)
    *(_T110 + 0) = _T111
    branch _L27
_L28:
    *(_T110 + 4) = _T82
    _T112 = 0
    *(_T110 + 8) = _T112
    _T113 = *(_T110 + 4)
    _T114 = *(_T110 + 8)
    _T115 = *(_T90 + 4)
    _T116 = *(_T90 + 8)
    _T117 = (_T113 + _T115)
    _T118 = (_T114 + _T116)
    *(_T101 + 4) = _T117
    *(_T101 + 8) = _T118
    *(_T6 + 8) = _T101
}

