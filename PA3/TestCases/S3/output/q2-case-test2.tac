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
    _T7 = 3
    _T8 = 0
    _T9 = (_T7 < _T8)
    if (_T9 == 0) branch _L10
    _T10 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T10
    call _PrintString
    call _Halt
_L10:
    _T11 = 4
    _T12 = (_T11 * _T7)
    _T13 = (_T11 + _T12)
    parm _T13
    _T14 =  call _Alloc
    *(_T14 + 0) = _T7
    _T15 = 0
    _T14 = (_T14 + _T13)
_L11:
    _T13 = (_T13 - _T11)
    if (_T13 == 0) branch _L12
    _T14 = (_T14 - _T11)
    *(_T14 + 0) = _T15
    branch _L11
_L12:
    _T16 = 6
    _T3 = _T16
    _T17 = 2
    _T4 = _T17
    _T18 = 3
    _T5 = _T18
    _T20 = 3
    _T21 = (_T3 * _T20)
    _T22 = 100
    _T19 = _T22
_L13:
    _T23 = 0
    _T24 = (_T21 == _T23)
    if (_T24 == 0) branch _L14
    _T25 = (_T4 + _T5)
    _T19 = _T25
_L14:
    _T26 = 3
    _T27 = (_T21 == _T26)
    if (_T27 == 0) branch _L15
    _T28 = 3
    _T29 = (_T4 + _T28)
    _T19 = _T29
_L15:
    _T30 = 9
    _T31 = (_T21 == _T30)
    if (_T31 == 0) branch _L16
    _T32 = 2
    _T33 = (_T5 * _T32)
    _T34 = 6
    _T35 = (_T33 + _T34)
    _T19 = _T35
_L16:
    _T4 = _T19
    parm _T4
    call _PrintInt
    _T36 = "\n"
    parm _T36
    call _PrintString
    _T37 = 3
    _T6 = _T37
    _T39 = 100
    _T38 = _T39
_L17:
    _T40 = 0
    _T41 = (_T6 == _T40)
    if (_T41 == 0) branch _L18
    _T42 = (_T4 + _T5)
    _T38 = _T42
_L18:
    _T43 = 3
    _T44 = (_T6 == _T43)
    if (_T44 == 0) branch _L19
    _T45 = 3
    _T46 = (_T4 + _T45)
    _T38 = _T46
_L19:
    _T47 = 6
    _T48 = (_T6 == _T47)
    if (_T48 == 0) branch _L20
    _T49 = 2
    _T50 = (_T5 * _T49)
    _T51 = 6
    _T52 = (_T50 + _T51)
    _T38 = _T52
_L20:
    _T4 = _T38
    parm _T4
    call _PrintInt
    _T53 = "\n"
    parm _T53
    call _PrintString
    _T55 = 100
    _T54 = _T55
_L21:
    _T56 = 0
    _T57 = (_T3 == _T56)
    if (_T57 == 0) branch _L22
    _T58 = (_T4 + _T5)
    _T54 = _T58
_L22:
    _T59 = 3
    _T60 = (_T3 == _T59)
    if (_T60 == 0) branch _L23
    _T61 = 3
    _T62 = (_T4 + _T61)
    _T54 = _T62
_L23:
    _T63 = 6
    _T64 = (_T3 == _T63)
    if (_T64 == 0) branch _L24
    _T65 = 2
    _T66 = (_T5 * _T65)
    _T67 = 6
    _T68 = (_T66 + _T67)
    _T54 = _T68
_L24:
    _T4 = _T54
    parm _T4
    call _PrintInt
    _T69 = "\n"
    parm _T69
    call _PrintString
    _T71 = 6
    _T72 = (_T3 - _T71)
    _T73 = 100
    _T70 = _T73
_L25:
    _T74 = 0
    _T75 = (_T72 == _T74)
    if (_T75 == 0) branch _L26
    _T76 = (_T4 + _T5)
    _T70 = _T76
_L26:
    _T77 = 3
    _T78 = (_T72 == _T77)
    if (_T78 == 0) branch _L27
    _T79 = 3
    _T80 = (_T4 + _T79)
    _T70 = _T80
_L27:
    _T81 = 9
    _T82 = (_T72 == _T81)
    if (_T82 == 0) branch _L28
    _T83 = 2
    _T84 = (_T5 * _T83)
    _T85 = 6
    _T86 = (_T84 + _T85)
    _T70 = _T86
_L28:
    _T4 = _T70
    parm _T4
    call _PrintInt
    _T87 = "\n"
    parm _T87
    call _PrintString
    _T89 = 100
    _T88 = _T89
_L29:
    _T90 = 0
    _T91 = (_T3 == _T90)
    if (_T91 == 0) branch _L30
    _T92 = (_T4 + _T5)
    _T88 = _T92
_L30:
    _T93 = 3
    _T94 = (_T3 == _T93)
    if (_T94 == 0) branch _L31
    _T95 = 3
    _T96 = (_T4 + _T95)
    _T88 = _T96
_L31:
    _T97 = 6
    _T98 = (_T3 == _T97)
    if (_T98 == 0) branch _L32
    _T99 = 2
    _T100 = (_T5 * _T99)
    _T101 = 6
    _T102 = (_T100 + _T101)
    _T88 = _T102
_L32:
    _T103 = 3
    _T104 = 0
    _T105 = (_T103 < _T104)
    if (_T105 == 0) branch _L33
    _T106 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T106
    call _PrintString
    call _Halt
_L33:
    _T107 = 4
    _T108 = (_T107 * _T103)
    _T109 = (_T107 + _T108)
    parm _T109
    _T110 =  call _Alloc
    *(_T110 + 0) = _T103
    _T111 = 0
    _T110 = (_T110 + _T109)
_L34:
    _T109 = (_T109 - _T107)
    if (_T109 == 0) branch _L35
    _T110 = (_T110 - _T107)
    *(_T110 + 0) = _T111
    branch _L34
_L35:
    *(_T110 + 4) = _T88
    _T112 = 0
    *(_T110 + 8) = _T112
    _T113 = *(_T110 + 4)
    _T114 = *(_T110 + 8)
    *(_T14 + 4) = _T113
    *(_T14 + 8) = _T114
    _T115 = *(_T14 + 4)
    parm _T115
    call _PrintInt
    _T116 = "+"
    parm _T116
    call _PrintString
    _T117 = *(_T14 + 8)
    parm _T117
    call _PrintInt
    _T118 = "j"
    parm _T118
    call _PrintString
    _T119 = "\n"
    parm _T119
    call _PrintString
    _T121 = 100
    _T120 = _T121
_L36:
    _T122 = 8
    _T123 = (_T3 == _T122)
    if (_T123 == 0) branch _L37
    _T124 = (_T4 + _T5)
    _T120 = _T124
_L37:
    _T125 = 3
    _T126 = (_T3 == _T125)
    if (_T126 == 0) branch _L38
    _T127 = (_T4 + _T3)
    _T120 = _T127
_L38:
    _T128 = 0
    _T129 = (_T3 == _T128)
    if (_T129 == 0) branch _L39
    _T130 = 8
    _T120 = _T130
_L39:
    _T131 = 3
    _T132 = 0
    _T133 = (_T131 < _T132)
    if (_T133 == 0) branch _L40
    _T134 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T134
    call _PrintString
    call _Halt
_L40:
    _T135 = 4
    _T136 = (_T135 * _T131)
    _T137 = (_T135 + _T136)
    parm _T137
    _T138 =  call _Alloc
    *(_T138 + 0) = _T131
    _T139 = 0
    _T138 = (_T138 + _T137)
_L41:
    _T137 = (_T137 - _T135)
    if (_T137 == 0) branch _L42
    _T138 = (_T138 - _T135)
    *(_T138 + 0) = _T139
    branch _L41
_L42:
    *(_T138 + 4) = _T120
    _T140 = 0
    *(_T138 + 8) = _T140
    _T141 = *(_T138 + 4)
    _T142 = *(_T138 + 8)
    *(_T14 + 4) = _T141
    *(_T14 + 8) = _T142
    _T143 = *(_T14 + 4)
    parm _T143
    call _PrintInt
    _T144 = "+"
    parm _T144
    call _PrintString
    _T145 = *(_T14 + 8)
    parm _T145
    call _PrintInt
    _T146 = "j"
    parm _T146
    call _PrintString
    _T147 = "\n"
    parm _T147
    call _PrintString
}

