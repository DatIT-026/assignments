// ============================================================
//  cart/cartManager.js  —  BƯỚC 2: Logic giỏ hàng & Closure
// ============================================================
//
//  Kiến thức luyện tập:
//    - Array methods: .findIndex(), .filter(), .map(), .reduce()
//    - Arrow functions
//    - Spread operator (...)
//    - Closure (createDiscount)
//    - Module pattern
//
// ============================================================

// ── State ────────────────────────────────────────────────────
//  cart là mảng nội bộ, chỉ thay đổi qua các hàm bên dưới.
//  Mỗi item trong cart: { id, name, price, qty }

let cart = [];


// ── 2.1  addToCart ──────────────────────────────────────────
//  Thêm sản phẩm vào giỏ.
//  - Nếu sản phẩm đã có trong cart → tăng qty lên (qty += amount)
//  - Nếu chưa có → thêm object mới vào cart
//  - Nếu amount <= 0 → không làm gì (return sớm)
//
//  @param {Object} product  - object sản phẩm từ products.js
//  @param {number} amount   - số lượng muốn thêm (default = 1)
//
//  TODO: Viết hàm bên dưới 👇

export function addToCart(product, amount = 1) {
  if (amount <= 0) return;
  const index = cart.findIndex(item => item.id === product.id);
  if (index === -1) cart.push({ id: product.id, name: product.title ?? product.name, price: product.price, qty: amount })
  else cart[index].qty += amount;
}


// ── 2.2  removeFromCart ─────────────────────────────────────
//  Xoá sản phẩm khỏi cart theo id.
//  Dùng .filter() — KHÔNG dùng splice().
//
//  @param {number} productId
//
//  TODO: Viết hàm bên dưới 👇

export function removeFromCart(productId) {
  cart = cart.filter(item => item.id !== productId);
}

// ── 2.3  updateQty ──────────────────────────────────────────
//  Cập nhật số lượng của 1 sản phẩm.
//  - Nếu qty mới <= 0 → gọi removeFromCart() để xoá luôn
//  - Dùng .map() để tạo cart mới (không mutate trực tiếp)
//
//  @param {number} productId
//  @param {number} newQty
//
//  TODO: Viết hàm bên dưới 👇

export function updateQty(productId, newQty) {
  if (newQty <= 0) return removeFromCart(productId);
  cart = cart.map(item => {
    if (item.id === productId) return { ...item, qty: newQty }
    return item;
  })
}


// ── 2.4  getTotal ───────────────────────────────────────────
//  Tính tổng tiền của toàn bộ giỏ hàng.
//  Dùng .reduce()  →  tổng của (item.price * item.qty)
//
//  @returns {number}
//
//  TODO: Viết hàm bên dưới 👇

export function getTotal() {
  return cart.reduce((total, item) => (total + item.price * item.qty), 0);
}

// ── 2.5  createDiscount  (CLOSURE) ──────────────────────────
//  Đây là bài tập về CLOSURE - hàm trả về hàm.
//
//  createDiscount(percent) nhận vào % giảm giá,
//  và TRẢ VỀ một hàm mới. Hàm mới đó nhận vào (amount)
//  và tính số tiền sau khi giảm.
//
//  Ví dụ sử dụng:
//    const apply10 = createDiscount(10);   // giảm 10%
//    apply10(500000);  // → 450000
//
//    const apply20 = createDiscount(20);   // giảm 20%
//    apply20(500000);  // → 400000
//
//  @param {number} percent  - % giảm (0–100)
//  @returns {Function}      - hàm nhận (amount) và trả về số tiền đã giảm
//
//  TODO: Viết hàm bên dưới 👇

export function createDiscount(percent) {
    if (percent >= 0 && percent <= 100) {
      return (amount) => amount * (1 - percent / 100)
    }
}


// ── 2.6  getCart ────────────────────────────────────────────
//  Trả về bản sao của cart (dùng spread để tránh mutate từ ngoài).
//  @returns {Array}

export function getCart() {
  return [...cart];
}

// ── 2.7  clearCart ──────────────────────────────────────────
//  Xoá toàn bộ giỏ hàng.
//
//  TODO: Viết hàm bên dưới 👇

export function restoreCart(savedCart) {
  cart = savedCart;
}

export function clearCart() {
  cart = []
}
