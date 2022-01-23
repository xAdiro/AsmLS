		org		100h
;main:
		;mov		ax, 0x0026
		;mov		bx, 0xBBCC
		;mov		cx, 0xDDEE
		;mov		dx, 0xFF00
		;call	zapRej

		;mov		ax, 0x2626
		;mov		bx, 0x2626
		;mov		cx, 0x2626
		;mov		dx, 0x2626

		;call	zapRej

		;call	zapEty

;koniec:
		;mov		ax, 4C00h
		;int		21h

;**** Zmienne ****
nazwFold                    db  'a:\ide\debug',0
nazwPlik					db	'0000', 47		;'00000'
							db	'deb.log',	0


;	|2 bajty|	|8 bajtów|	|1 bajt|	|? bajtów|
;	nr linii	rejestry	flagi		? bajtów ze stosu

dane						dw	0				;nr linii
            TIMES	529		db	0				;rejestry, stos etc.

;**** Funkcje ****
%include "rejestry.asm"


;[F] Zapisuje numer linii spod DX i stan rejestrów do pliku
zapLinie:
		pushf
        pusha

		call	.rejDoZm
		call    .ustFold
		call	.utwLog

		mov		ah, 3dh
		mov		al, 1
		mov		dx, nazwPlik
		int		21h				;wrzuc uchwyt pliku do AX

		mov		bx, ax
		;;;;;					Z bliżej nieznanego mi powodu ten fragment nie robi tego co powinien, tj nie ustawia uchywtu na koniec pliku
		;mov		ah, 42h
		;mov		al, 2
		;xor		cx, cx
		;xor		dx, dx
		;int		21h
		;call	wyswRej
		;jnc		.noErr
;.noErr:
		;;;;

		mov		dx, dane		;wrzuc adres danych do zapisu, do DX
		mov		ah, 40h
		mov		cx,16			;zapisz piewrsze 16 bajtow (8 rejestrow)
		int		21h

		mov		ah, 3eh			;zamknij plik
		int		21h

		popa
		popf
		ret
;[f] Zmienia folder - cd nazwFold
.ustFold:

        mov     dx, nazwFold
        mov     ah, 3Bh
        int     21h
        jnc		.noErr2


        mov		dl, al
        add		dl, '0'			;Wyswietla bład w razie czego
        mov		ah, 2
        xor		al, al
        int		21h

.noErr2:




        ret

;[f] Zapisuje rejestry i flagi do zmiennej "dane"
.rejDoZm:
		pushf
		push	DI
		push	SI
		push 	BP
		push	SP
		push	DX
		push	CX
		push	BX
		push	AX

		mov		bp, sp

		;mov		dx, [bp+18]
		;mov		[dane], dh
		;mov		[dane+1], dl


		mov		bx, dane+2

		mov		cx, 9
.l1:
		mov		ax, [bp]
		mov		[bx], ah
		mov		[bx+1], al

		add		bp, 2
		add		bx, 2
		loop	.l1

		pop		AX
		pop		BX
		pop		CX
		pop		DX
		pop		SP
		pop		BP
		pop		SI
		pop		DI
		popf
		ret

;[F] Tworzy plik o nazwie spod nazwPlik
.utwLog:
		call	.zwiekszN

		mov		ah, 3Ch
		mov		cx, 0
		mov		dx, nazwPlik
		int		21h
		jnc		.noErr		;jesli dziala skoncz


		add		al, '0'
		mov		ah, 9		;wyswietlanie w kolorze
		mov		bx, 4		;kolor czerwony
		mov		cx, 10		;3 znaki
		int		10h
		;mov		al, '!'		;znak do wysietlenia
		int		10h
		jmp		.koniec


.noErr:
		mov		bx, ax
		mov		ah, 3eh			;zamknij plik
		int		21h
		ret

;[f] Zwiększa indeks w nazwie pliku
.zwiekszN:
		mov		bx, nazwPlik+4

.l2:

		cmp		byte [bx], '9'
		je		.dziewiec

;normalnie:
		;mov		al, [bx]
		;inc		al
		;mov		[bx], al
		inc     byte [bx]
		jmp		.koniec


.dziewiec:
		;mov		al, '0'
		;mov		[bx], al
		mov		byte [bx], '0'
		dec		bx

		jmp		.l2

.koniec:

		ret


