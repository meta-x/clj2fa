(ns clj2fa.main
  (:require [clj-otp.core :as otp-core]
            [clj-otp.qrcode :as otp-qr]
            [clj-otp.base32 :as otp-b32])
  )

; http://nakkaya.com/2012/08/13/google-hotp-totp-two-factor-authentication-for-clojure/
(defn -main [& args]
  (println "<setup-phase>")

  (println "starty by generating a seed and persisting it somewhere (e.g. database)")
  (def seed (otp-core/secret-key))
  (println "seed: " (otp-b32/encode-data seed))

  (println "now show the qr code to the user so they can setup OTP on the google authenticator app (or in alternative, manually inputing the seed)")
  (let [seed-str (otp-b32/encode-data seed)
        totp-url (otp-qr/totp-url "some-label-read https://code.google.com/p/google-authenticator/wiki/KeyUriFormat" seed-str)
        hotp-url (otp-qr/hotp-url "some-label-read https://code.google.com/p/google-authenticator/wiki/KeyUriFormat" 666 seed-str)]
    (println "seed: " seed-str)
    (println "totp url: " totp-url)
    (println "hotp url: " hotp-url)
  )

  (println "</setup-phase>")
  (println "hit ENTER to continue")
  (read-line)

  (println "<code-validation>")

  (println "this is how a code is generated on the server")
  (println "in order to validate the OTP, code inputted by user must match the code generated here")
  (loop [c 667]
    (let [totp-code (otp-core/totp seed)
          hotp-code (otp-core/hotp seed c)]
      (println "TOTP code: " totp-code)
      (println (otp-core/accept? totp-code seed)) ; this has to always return true

      (println "HOTP code: " hotp-code)
      (println (otp-core/accept? hotp-code seed)) ; this has to always return true

      (println "hit ENTER to generate a new code or CTRL+C to exit")
      (read-line)
      (recur (inc c))
    ))
  (println "</code-validation>")

  )
