// ============================================================
//  api/productApi.js  —  BƯỚC 4: Fetch API & Async/Await
// ============================================================
//
//  Kiến thức luyện tập:
//    - fetch()
//    - async / await
//    - Promise.all()
//    - try / catch / finally
//    - Response.ok  &  Response.json()
//    - Xử lý loading state & error state
//
//  API miễn phí sử dụng:  https://fakestoreapi.com
//    - GET /products          → danh sách sản phẩm
//    - GET /products/categories → danh sách danh mục
//    - GET /products/{id}     → 1 sản phẩm theo id
//
// ============================================================

const BASE_URL = 'https://fakestoreapi.com';


// ── 4.1  fetchProducts ──────────────────────────────────────
//  Fetch danh sách sản phẩm từ API.
//
//  Quy trình:
//    1. Gọi fetch(url)
//    2. Kiểm tra response.ok — nếu false thì throw new Error(...)
//    3. Parse JSON bằng response.json()
//    4. Return dữ liệu
//    5. Wrap trong try/catch — catch trả về mảng rỗng []
//       và log lỗi ra console
//
//  @returns {Promise<Array>}  - mảng sản phẩm hoặc []
//
//  TODO: Viết hàm bên dưới 👇

export async function fetchProducts() {
  try {
    const response = await fetch(BASE_URL + "/products");
    if (!response.ok) throw new Error("Server error: " + response.status);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error: " + error.message);
    return [];
  }
}


// ── 4.2  fetchCategories ────────────────────────────────────
//  Fetch danh sách categories từ API.
//  Tương tự fetchProducts nhưng endpoint là /products/categories
//
//  @returns {Promise<Array>}
//
//  TODO: Viết hàm bên dưới 👇

export async function fetchCategories() {
  try {
    const response = await fetch(BASE_URL + "/products/categories");
    if (!response.ok) throw new Error("Server error: " + response.status);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error: " + error.message);
    return [];
  }
}


// ── 4.3  fetchProductById ───────────────────────────────────
//  Fetch 1 sản phẩm theo id.
//  Endpoint: /products/{id}
//
//  @param {number} id
//  @returns {Promise<Object|null>}  - object sản phẩm hoặc null nếu lỗi
//
//  TODO: Viết hàm bên dưới 👇

export async function fetchProductById(id) {
  try {
    const response = await fetch(BASE_URL + "/products/" + id);
    if (!response.ok) throw new Error("Server error: " + response.status);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error: " + error.message);
    return null;
  }
}


// ── 4.4  fetchProductsAndCategories ─────────────────────────
//  Fetch ĐỒNG THỜI cả products và categories (không fetch tuần tự).
//  Dùng Promise.all() để gọi cả 2 cùng lúc → nhanh hơn.
//
//  Ví dụ:
//    const [products, categories] = await Promise.all([...])
//
//  @returns {Promise<{ products: Array, categories: Array }>}
//
//  TODO: Viết hàm bên dưới 👇

export async function fetchProductsAndCategories() {
  try {
    const [res1, res2] = await Promise.all([
      fetch(BASE_URL + "/products"),
      fetch(BASE_URL + "/products/categories")
    ]);

    if (!res1.ok) throw new Error("Server error: " + res1.status);
    if (!res2.ok) throw new Error("Server error: " + res2.status);

    const products = await res1.json();
    const categories = await res2.json();

    return { products, categories };
  } catch (error) {
    console.error("Error: " + error.message);
    return null;
  }
}


// ── 4.5  fetchWithLoading ───────────────────────────────────
//  Wrapper cao cấp hơn: nhận vào một async function,
//  tự động set loading/error state lên DOM trước và sau khi gọi.
//
//  Cách dùng:
//    const data = await fetchWithLoading(fetchProducts);
//
//  Quy trình:
//    1. Hiện #loading-state (display: block), ẩn #error-state
//    2. Gọi asyncFn()
//    3. Ẩn #loading-state
//    4. Nếu lỗi → hiện #error-state với message, return null
//    5. Return kết quả
//
//  @param {Function} asyncFn  - async function cần gọi
//  @returns {Promise<any>}
//
//  TODO: Viết hàm bên dưới 👇

export async function fetchWithLoading(asyncFn) {
  const loadingEl = document.getElementById('loading-state');
  const errorEl   = document.getElementById('error-state');

  try {
    loadingEl.style.display = 'block';
    errorEl.style.display = 'none';
    return await asyncFn()
  } catch (error) {
    errorEl.style.display = 'block';
    errorEl.textContent = error.message;
    return null;
  } finally {
    loadingEl.style.display = 'none';
  }
}
