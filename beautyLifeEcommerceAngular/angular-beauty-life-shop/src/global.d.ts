declare var paypal: any;
if (typeof window !== 'undefined' && typeof localStorage !== 'undefined') {
  // Safe to access localStorage
  const token = localStorage.getItem('token');
} else {
  console.warn('localStorage is not available in this environment');
}
