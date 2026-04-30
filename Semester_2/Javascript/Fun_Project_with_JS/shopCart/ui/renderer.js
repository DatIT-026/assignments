// ============================================================
//  ui/renderer.js  —  BƯỚC 5: DOM Manipulation & localStorage
// ============================================================
//
//  Kiến thức luyện tập:
//    - localStorage.setItem / getItem / removeItem
//    - JSON.stringify() / JSON.parse()
//    - Template literals  (`${variable}`)
//    - innerHTML
//    - addEventListener
//    - DOM querySelector
//
// ============================================================

import { getCart, addToCart, removeFromCart, updateQty, getTotal } from '../cart/cartManager.js';


// ── 5.1  saveCartToStorage ──────────────────────────────────
//  Lưu giỏ hàng hiện tại vào localStorage.
//  Key: "shopcart_data"
//  Phải dùng JSON.stringify() vì localStorage chỉ lưu string.
//
//  TODO: Viết hàm bên dưới 👇

export function saveCartToStorage() {
  localStorage.setItem('shopcart_data', JSON.stringify(getCart()));
}


// ── 5.2  loadCartFromStorage ────────────────────────────────
//  Đọc giỏ hàng từ localStorage khi trang load lại.
//  Dùng JSON.parse() để chuyển string → object.
//  Nếu chưa có data → trả về mảng rỗng [].
//
//  ⚠️  Dùng try/catch vì JSON.parse có thể throw nếu data bị lỗi.
//
//  @returns {Array}
//
//  TODO: Viết hàm bên dưới 👇

export function loadCartFromStorage() {
  try {
    const data = localStorage.getItem('shopcart_data');
    if (!data) return[];
    return JSON.parse(data);
  } catch (error) {
    console.log("Error: " + error.message);
    return[];
  }
}


// ── 5.3  renderProducts ─────────────────────────────────────
//  Render danh sách sản phẩm ra #product-list bằng innerHTML.
//  Dùng template literal để tạo HTML cho mỗi sản phẩm.
//
//  Mỗi sản phẩm hiển thị: tên, giá (đã format), category, stock.
//  Nút "Thêm vào giỏ" có data-id="${product.id}".
//
//  Nếu products rỗng → hiển thị "<p>Không tìm thấy sản phẩm.</p>"
//
//  Gợi ý cấu trúc HTML mỗi card:
//  <div class="product-card">
//    <h3>${product.title}</h3>
//    <p class="price">${price} VND</p>
//    <p class="category">${product.category}</p>
//    <button data-id="${product.id}">Thêm vào giỏ</button>
//  </div>
//
//  @param {Array} products
//
//  TODO: Viết hàm bên dưới 👇

export function renderProducts(products) {
  const container = document.getElementById('product-list');

  if (products.length === 0) {
    container.innerHTML = '<p>Không tìm thấy sản phâm.</p>'
    return;
  }

  container.innerHTML = products.map(p => 
    `
    <div class="product-card">
      <h3>${p.title ?? p.name}</h3>
      <p class="price">${p.price.toLocaleString('vi-VN')} VND</p>
      <p class="category">${p.category}</p>
      <button data-id="${p.id}">Thêm vào giỏ</button>
    </div>
    `
  ).join('');
}


// ── 5.4  renderCart ─────────────────────────────────────────
//  Render giỏ hàng ra #cart-items và tổng tiền ra #cart-summary.
//
//  Mỗi cart item hiển thị:
//    - Tên sản phẩm
//    - Nút "-"  (data-action="decrease" data-id="${item.id}")
//    - Số lượng hiện tại
//    - Nút "+"  (data-action="increase" data-id="${item.id}")
//    - Giá x qty
//    - Nút "Xoá" (data-action="remove" data-id="${item.id}")
//
//  #cart-summary hiển thị:
//    - Số lượng sản phẩm
//    - Tổng tiền (dùng getTotal())
//
//  Nếu giỏ rỗng → hiển thị "Giỏ hàng trống."
//
//  TODO: Viết hàm bên dưới 👇

export function renderCart() {
  const cartItems  = document.getElementById('cart-items');
  const cartSummary = document.getElementById('cart-summary');
  const cart = getCart();

  if (cart.length === 0) {
    cartItems.innerHTML = '<p>Giỏ hàng trống.</p>'
    cartSummary.innerHTML = '';
    return;
  }

  cartItems.innerHTML = cart.map(item => 
    `
    <div class="cart-item">
      <span>${item.name}</span>
      <div class="qty-controls">
        <button data-action="decrease" data-id="${item.id}">-</button>
        <span>${item.qty}</span>
        <button data-action="increase" data-id="${item.id}">+</button>
      </div>
      <span>${(item.price * item.qty).toLocaleString('vi-VN')} VND</span>
      <button class="remove-btn" data-action="remove" data-id="${item.id}">🗑️</button>
    </div>
    `
  ).join('');

  cartSummary.innerHTML = 
  `
  <p>Số sản phẩm: ${cart.length}</p>
  <p class="total">Tổng tiền: ${getTotal().toLocaleString('vi-VN')} VND</p>
  `
}


// ── 5.5  updateCartCount ────────────────────────────────────
//  Cập nhật số lượng sản phẩm trên icon giỏ hàng (#cart-count).
//  Tổng số = tổng tất cả item.qty trong cart.
//  Dùng .reduce()
//
//  TODO: Viết hàm bên dưới 👇

export function updateCartCount() {
  const countEl = document.getElementById('cart-count');
  const cart = getCart();
  countEl.textContent = cart.reduce((total, item) => (total + item.qty), 0);
}


// ── 5.6  attachCartEvents ───────────────────────────────────
//  Gắn event listener cho #cart-items (dùng event delegation).
//  Event delegation: gắn 1 listener lên cha, kiểm tra e.target.dataset.
//
//  Khi click vào button trong cart:
//    - data-action="increase" → updateQty(id, qty + 1)
//    - data-action="decrease" → updateQty(id, qty - 1)
//    - data-action="remove"   → removeFromCart(id)
//  Sau mỗi action: gọi renderCart(), updateCartCount(), saveCartToStorage()
//
//  TODO: Viết hàm bên dưới 👇

export function attachCartEvents() {
  const cartItems = document.getElementById('cart-items');

  cartItems.addEventListener('click', (e) => {
    const { action, id } = e.target.dataset;
    if (!action || !id) return;

    const productId = Number(id);
    const cart = getCart();
    const item = cart.find(i => i.id === productId);

    if (action === "increase") updateQty(productId, item.qty + 1);
    if (action === "decrease") updateQty(productId, item.qty - 1);
    if (action === "remove") removeFromCart(productId);

    renderCart();
    updateCartCount();
    saveCartToStorage();
  });
}


// ── 5.7  attachProductEvents ────────────────────────────────
//  Gắn event listener cho #product-list (event delegation).
//  Khi click nút "Thêm vào giỏ":
//    - Lấy data-id từ button
//    - Tìm sản phẩm trong danh sách (truyền vào từ ngoài)
//    - Gọi addToCart(product)
//    - Gọi renderCart(), updateCartCount(), saveCartToStorage()
//    - BONUS: thêm class "added" vào .product-card cha rồi xoá sau 600ms
//
//  @param {Array} productList  - mảng sản phẩm hiện tại đang hiển thị
//
//  TODO: Viết hàm bên dưới 👇

export function attachProductEvents(productList) {
  const container = document.getElementById('product-list');

  container.addEventListener('click', (e) => {
    const id = Number(e.target.dataset.id);
    if (!id) return;

    const product = productList.find(p => p.id === id);
    if (!product) return

    addToCart(product);
    renderCart();
    updateCartCount();
    saveCartToStorage();

    const card = e.target.closest('.product-card');
    if (card) {
      card.classList.add('added');
      setTimeout(() => card.classList.remove('added'), 600);
    }
  });
}
