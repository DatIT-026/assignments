// ============================================================
//  main.js  —  Điều phối toàn bộ ứng dụng
// ============================================================
//
//  File này kết nối tất cả các module lại với nhau.
//  Bạn chỉ cần hoàn thành các TODO bên dưới sau khi
//  đã viết xong code ở các file bước 1–5.
//
// ============================================================

import { products, filterByCategory, sortByPrice, searchProducts } from './data/products.js';
import { fetchProductsAndCategories, fetchWithLoading }             from './api/productApi.js';
import { validateAll, validateCoupon }                                               from './validation/validate.js';
import { createDiscount, getCart, clearCart, getTotal, restoreCart }                        from './cart/cartManager.js';
import {
  saveCartToStorage,
  loadCartFromStorage,
  renderProducts,
  renderCart,
  updateCartCount,
  attachCartEvents,
  attachProductEvents
} from './ui/renderer.js';


// ── State toàn cục ───────────────────────────────────────────
let allProducts  = [];   // toàn bộ sản phẩm (từ API hoặc local)
let displayList  = [];   // sản phẩm đang hiển thị (sau filter/sort/search)
let discountFn   = null; // hàm giảm giá hiện tại (closure từ createDiscount)


// ── Helpers ─────────────────────────────────────────────────
function applyFiltersAndRender() {
  // TODO: Đọc giá trị từ 3 input (#search-input, #category-select, #sort-select)
  //       Rồi lần lượt: filterByCategory → searchProducts → sortByPrice
  //       Gán kết quả vào displayList
  //       Gọi renderProducts(displayList) và attachProductEvents(displayList)

  const keyword = document.getElementById('search-input').value;
  const category = document.getElementById('category-select').value;
  const order = document.getElementById('sort-select').value;

  let result = filterByCategory(allProducts, category);
  result = searchProducts(result, keyword);
  result = sortByPrice(result, order);
  displayList = result;

  renderProducts(displayList);
  attachProductEvents(displayList);
}


// ── Khởi động ứng dụng ──────────────────────────────────────
async function init() {
  // TODO 1: Thử fetch từ API bằng fetchWithLoading(fetchProductsAndCategories)
  //         Nếu thành công → dùng allProducts = data.products
  //         Nếu thất bại (null) → dùng allProducts = products (data local)
  const data = await fetchWithLoading(fetchProductsAndCategories);
  if (data) allProducts = data.products;
  else allProducts = products

  // TODO 2: Gọi applyFiltersAndRender() lần đầu để hiển thị sản phẩm
  applyFiltersAndRender();

  // TODO 3: Gắn event listener cho search/filter/sort
  document.getElementById('search-input').addEventListener('input', applyFiltersAndRender);
  document.getElementById('category-select').addEventListener('change', applyFiltersAndRender);
  document.getElementById('sort-select').addEventListener('change', applyFiltersAndRender);

  // TODO 4: Gọi attachCartEvents() để xử lý click trong giỏ hàng
  attachCartEvents();

  // TODO 5: Load giỏ hàng từ localStorage rồi renderCart() + updateCartCount()
  //   Gợi ý: loadCartFromStorage() trả về mảng - bạn cần khôi phục vào cartManager
  //          (Xem lại cart/cartManager.js - thêm hàm restoreCart nếu cần)
  const savedCart = loadCartFromStorage();
  if (savedCart.length > 0) {
    restoreCart(savedCart);
    renderCart();
    updateCartCount();
  }

  // TODO 6: Checkout form
  document.getElementById('btn-apply-coupon').addEventListener('click', () => {
    const couponInput = document.getElementById('input-coupon').value;
    // TODO: validate coupon, nếu hợp lệ thì createDiscount(10) → gán vào discountFn
    //       Hiển thị thông báo giảm giá áp dụng thành công
    const result = validateCoupon(couponInput);
    if (result.valid) {
      discountFn = createDiscount(10);
      alert("Áp dụng mã giảm giá thành công! Giảm 10%");
    } else alert(result.message);
  });

  document.getElementById('checkout-form').addEventListener('submit', (e) => {
    e.preventDefault();
    const email  = document.getElementById('input-email').value;
    const phone  = document.getElementById('input-phone').value;
    const coupon = document.getElementById('input-coupon').value;

    // TODO: Gọi validateAll({ email, phone, coupon })
    //       Nếu có lỗi → hiển thị message vào các #error-* span + thêm class "invalid"
    //       Nếu hợp lệ  → tính finalTotal (áp dụng discountFn nếu có)
    //                   → alert("Đặt hàng thành công! Tổng: ...")
    //                   → clearCart(), renderCart(), updateCartCount()
    //                   → saveCartToStorage()
    const { isValid, errors } = validateAll({ email, phone, coupon });

    if (!isValid) {
      document.getElementById('error-email').textContent = errors.email;
      document.getElementById('error-phone').textContent = errors.phone;
      document.getElementById('error-coupon').textContent = errors.coupon;
      return;
    }

    const total = getTotal();
    const finalTotal = discountFn ? discountFn(total) : total;
    alert(`Đặt hàng thành công! Tổng ${finalTotal.toLocaleString('vi-VN')} VND`);
    clearCart();
    renderCart();
    updateCartCount();
    saveCartToStorage();
  });
}

init();
