// ============================================================
//  validation/validate.js  —  BƯỚC 3: Validate với RegExp
// ============================================================
//
//  Kiến thức luyện tập:
//    - RegExp literals:  /pattern/flags
//    - RegExp constructor: new RegExp(pattern, flags)
//    - .test(string) → trả về true/false
//    - String.trim()
//
// ============================================================


// ── 3.1  validateEmail ──────────────────────────────────────
//  Email hợp lệ phải có dạng:  something@something.something
//  Ví dụ hợp lệ:   user@gmail.com  |  hello.world@company.vn
//  Ví dụ sai:       user@  |  @gmail.com  |  usergmail.com
//
//  Gợi ý regex:  /^[^\s@]+@[^\s@]+\.[^\s@]+$/
//  Giải thích:
//    ^         - bắt đầu chuỗi
//    [^\s@]+   - 1+ ký tự (không phải space hay @)
//    @         - dấu @
//    [^\s@]+   - tên domain
//    \.        - dấu chấm (escape vì . trong regex = "bất kỳ ký tự")
//    [^\s@]+   - phần đuôi (.com, .vn, ...)
//    $         - kết thúc chuỗi
//
//  @param {string} email
//  @returns {{ valid: boolean, message: string }}
//
//  TODO: Viết hàm bên dưới 👇

export function validateEmail(email) {
  email = email.trim();
  const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (regex.test(email)) return { valid: true, message: "" }
  else return { valid: false, message: "Invalid Email!" }
}

// ── 3.2  validatePhone ──────────────────────────────────────
//  Số điện thoại Việt Nam hợp lệ:
//    - Bắt đầu bằng 0
//    - Tiếp theo là: 3, 5, 7, 8, 9
//    - Tổng cộng 10 chữ số
//
//  Ví dụ hợp lệ:   0912345678  |  0387654321  |  0765432198
//  Ví dụ sai:       0212345678  |  123456789   |  09123456789
//
//  Gợi ý regex:  /^0[35789]\d{8}$/
//  Giải thích:
//    0         - bắt đầu bằng 0
//    [35789]   - ký tự thứ 2 là một trong các số này
//    \d{8}     - tiếp theo là đúng 8 chữ số
//
//  @param {string} phone
//  @returns {{ valid: boolean, message: string }}
//
//  TODO: Viết hàm bên dưới 👇

export function validatePhone(phone) {
  phone = phone.trim();
  const regex = /^0[35789]\d{8}$/;
  if (regex.test(phone)) return { valid: true, message: "" }
  else return { valid: false, message: "Invalid phone number!" }
}


// ── 3.3  validateCoupon ─────────────────────────────────────
//  Mã giảm giá hợp lệ:
//    - Đúng 2 chữ cái IN HOA  +  đúng 4 chữ số
//    - Ví dụ hợp lệ:   VN2024  |  AB1234  |  XY9999
//    - Ví dụ sai:       vn2024  |  VN123   |  VNX234  |  VN12345
//
//  Gợi ý regex:  /^[A-Z]{2}\d{4}$/
//
//  @param {string} coupon
//  @returns {{ valid: boolean, message: string }}
//
//  TODO: Viết hàm bên dưới 👇

export function validateCoupon(coupon) {
  coupon = coupon.trim();
  const regex = /^[A-Z]{2}\d{4}$/;
  if (regex.test(coupon)) return { valid: true, message: "" }
  else return { valid: false, message: "Coupon is not valid or expired!" }
}


// ── 3.4  validateAll ────────────────────────────────────────
//  Chạy cả 3 validate cùng lúc.
//  Trả về object tổng hợp kết quả.
//
//  @param {{ email: string, phone: string, coupon: string }} formData
//  @returns {{ isValid: boolean, errors: { email, phone, coupon } }}
//
//  TODO: Viết hàm bên dưới 👇

export function validateAll({ email, phone, coupon }) {
  const emailResult  = validateEmail(email);
  const phoneResult  = validatePhone(phone);
  const couponResult = validateCoupon(coupon);

  // TODO: Tổng hợp lại:
  // isValid = true nếu CẢ 3 đều valid
  // errors  = { email: message, phone: message, coupon: message }
  //           (chỉ message khi invalid, hoặc "" khi valid)
  let isValid = emailResult.valid && phoneResult.valid && couponResult.valid;
  return {
    isValid,
    errors: {
      email: emailResult.valid ? "" : emailResult.message,
      phone: phoneResult.valid ? "" : phoneResult.message,
      coupon: couponResult.valid ? "" : couponResult.message
    }
  };
}