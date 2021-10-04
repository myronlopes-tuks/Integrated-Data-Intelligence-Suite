const pdfTemplate = () => {
    return (
   JSON.parse("[\n" +
      "  {\n" +
      "  \"pdf\": \"JVBERi0xLjQKJeLjz9MKMiAwIG9iago8PC9MZW5ndGggMTI0Mi9GaWx0ZXIvRmxhdGVEZWNvZGU+PnN0cmVhbQp4nM1YTXPbNhC961fs0Z1paRL8zqmu5WTcNo0Tc6bHDizBFhN+xCRlV/++IAGkDBcL2pdO7ZEl6+1bPLwFsSAfN78UmzCBzE+g2G98+CmYPpy/DSBgUNxvzj6Jr203QM33AtoGfig+b8788Dzwz5nPAvDDNyx+w/zxex98kyUbP5zB4tsp91nRiWYPFw2vTn3Zv4TI2Pf4TN5yCMW/PdY1706vEXUQcN0M4qHjg9jDlg8cSvl/VZUPotkJ6I/lIOC+PUrtCQzjHHoZAYMk7sfoXVtVYjeS707Tt73g3e7wIg0uZRdNW0urXmVYyP5XfjFQkyjFf2NZIf4ejrx6uWVXxebj5nF6MfhVfvtOvj9vsswLIckz+fLSECIfOrG5naAgzjyfAhlLvTSlwDz1YgILk9RjVNYoTLzcltWX+YzY1Ncgy+agkUvARjAFK8kEakQTsJFNwEp2xJyyCdjIpmAlm0CNbAI2sglYyQ4ip2wCNrIpWMkmUCObgI1sAp5kx1nikk3BWjYJT7IpVMumYC2bgpXsOHPKJmAjm4KVbAI1sgnYyCZgJTuwX69GNgEb2RSsZBOokU3ARjaCP47nAdnU5W8AuS/VpyyCop53kKtmKIepVchdcxYeJP44H4oAxemrwCzG8nGamHXxJDr+MDGWsWNEvTkb20/Hd0PZNpa8eS6nitO+7cTjUTYoywRC6QWzTeDDKKWq5lJMrJZyK+Qca/kHZ43CbPQaZ33f9sM8pQnUKW+6ti4bnXIZ4/tTzO/tjtunP9UuyZZjfirm2VRQqsb7edt+5pfcMgNdWJztQ/fAm7IfJczTmnid2FYcVUickXk+VUocnNiCdWFw9E3bl0P5ZFmC2lRM+UMefA+iaywVMJO75LZFrYyNk0W+4sCbfUsbjAgrBkfRmsEoYywdzsLxx3JhmYSh+knIUqC0kasUKHq9FIjiKoVW7SqF3Ja/z7c9dnfcYpyxFhHoS82YhyiRazWjaOdqRtHrFiKKy8IgWbVQbTmvsBAR3Ks5zrK11Ywy+g6DUbDTYBS9bjCiOAw2k3MYHKsdZZbvtj0OB7Rfx4na+y/uu3Ln8B/nW/Ff56X9xxkd/uNgl/84etV/THH5ryfn8j96QbOMQ90sf+Mdv2v/wgFMDfS+/fLQfqH3ejzaSnXCtWaKM7qqg4JdOziOXq8OoriqE6JmStyLhwmDKMi8SPqCDtphlCxAfJwNE3TO/PZkYiuG6WEEsUDCaLmj8h7ujw3IN7UXgrwe4U5cwzNvBpBX7/RMYycPvz/CNdRl30N9gl4OV8Fly/uBVzC0z03vnnSQ+8B8WYlv8/r3MQLGZoYEaWQFNZVAFVXe0TioBKqo8o7ZQSVQRWWJi0qgihrYjdBUAp2oucslApyIqcsjAlTPDVwOEeBEjFz+IBAv/yBfbpp/tp1lwatdAkdvx3sSPj7euxHdTt6c6Ds0y+USpMszHHVg0IOh+CDwUssZVGVPlt136pZAdkU9CKLFXpZRY1g6AtC3SXoERHKNgA5p4wi6t5AtRA+EuKGXM2qgYFlJ6l5EJ0fxjuT5snDjJG62lwe5VKgBEId5eUTkz5Y1c2ZG0Y7MqBko+/vyatfKtmB5RqAPl0te4OXUSkVdQ41RP4mqPFTiXXtHTgVRZ8P8A/N7rUEKZW5kc3RyZWFtCmVuZG9iago0IDAgb2JqCjw8L1R5cGUvUGFnZS9NZWRpYUJveFswIDAgNTk1IDg0Ml0vUmVzb3VyY2VzPDwvRm9udDw8L0YxIDEgMCBSPj4+Pi9Db250ZW50cyAyIDAgUi9QYXJlbnQgMyAwIFI+PgplbmRvYmoKNSAwIG9iago8PC9MZW5ndGggMzAxL0ZpbHRlci9GbGF0ZURlY29kZT4+c3RyZWFtCnicjZHNToQwFIX3fYq7HBdiW6DQ3WhEjTqaEV6gQzuCQpsZIMYY393ysxhBiAtC03O+c+DeA7pKkMsgxAwSiTCcM284kLA9XNwQIBSSPVrBWfJmBfxLX8VNWYrj55/amOg6VgJqU4sCzL5NzoSELH/NQJoy10KnCnINdaYgNUWh0lpJkKIW/yqY3kYJ2qJD91C4t7e3CDs+fKAwdFwIOAaKuUPBfttRoXgQKQ/se07t0cBbQmfUHvXDJXRG7VGPLqETddvul0A7DAIcd79st1me7nX9pHZNYZrqMn6A9WOjrxutXbcdop3eCU8576YyDiAOZ1N33xawkXljKlXBRuxyKWYrJtRSBcMjc1S9m3I2e2Jfyva8kfnrObmLXuLv2fgJ4WOHh4P9B79awE4KZW5kc3RyZWFtCmVuZG9iago2IDAgb2JqCjw8L1R5cGUvUGFnZS9NZWRpYUJveFswIDAgNTk1IDg0Ml0vUmVzb3VyY2VzPDwvRm9udDw8L0YxIDEgMCBSPj4+Pi9Db250ZW50cyA1IDAgUi9QYXJlbnQgMyAwIFI+PgplbmRvYmoKMSAwIG9iago8PC9UeXBlL0ZvbnQvU3VidHlwZS9UeXBlMS9CYXNlRm9udC9IZWx2ZXRpY2EvRW5jb2RpbmcvV2luQW5zaUVuY29kaW5nPj4KZW5kb2JqCjMgMCBvYmoKPDwvVHlwZS9QYWdlcy9Db3VudCAyL0tpZHNbNCAwIFIgNiAwIFJdPj4KZW5kb2JqCjcgMCBvYmoKPDwvVHlwZS9DYXRhbG9nL1BhZ2VzIDMgMCBSPj4KZW5kb2JqCjggMCBvYmoKPDwvUHJvZHVjZXIoaVRleHSuIDUuNS4xMy4yIKkyMDAwLTIwMjAgaVRleHQgR3JvdXAgTlYgXChBR1BMLXZlcnNpb25cKSkvQ3JlYXRpb25EYXRlKEQ6MjAyMTEwMDMwMzI1MjArMDInMDAnKS9Nb2REYXRlKEQ6MjAyMTEwMDMwMzI1MjArMDInMDAnKT4+CmVuZG9iagp4cmVmCjAgOQowMDAwMDAwMDAwIDY1NTM1IGYgCjAwMDAwMDE5MTcgMDAwMDAgbiAKMDAwMDAwMDAxNSAwMDAwMCBuIAowMDAwMDAyMDA1IDAwMDAwIG4gCjAwMDAwMDEzMjUgMDAwMDAgbiAKMDAwMDAwMTQzNyAwMDAwMCBuIAowMDAwMDAxODA1IDAwMDAwIG4gCjAwMDAwMDIwNjIgMDAwMDAgbiAKMDAwMDAwMjEwNyAwMDAwMCBuIAp0cmFpbGVyCjw8L1NpemUgOS9Sb290IDcgMCBSL0luZm8gOCAwIFIvSUQgWzw3NzQ0ZmVmYjk4YjBiZDg4MDFiMzA0Mjg3NDM4ZDVlMD48Nzc0NGZlZmI5OGIwYmQ4ODAxYjMwNDI4NzQzOGQ1ZTA+XT4+CiVpVGV4dC01LjUuMTMuMgpzdGFydHhyZWYKMjI2NwolJUVPRgo=\",\n" +
      "  \"name\": \"UserReport\",\n" +
      "  \"date\": \"03/10/2021 03:25:20\",\n" +
      "  \"id\": \"5414e9b8-8b65-4f54-b988-67383685cf89\",\n" +
      "  \"fallback\": false,\n" +
      "  \"fallbackMessage\": \"\"\n" +
      "  }\n" +
      "]")
    );
};

export default pdfTemplate;